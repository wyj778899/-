package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;
    @Autowired
    private UserClient userClient;

    //添加(关注)好友
    public void addFriend(String userid,String friendid){
        //查询是否已经添加过好友
        if(friendDao.getCountByUserIdAndFriendId(userid, friendid)==1){
            //已经添加=>提示
            throw  new  RuntimeException("请勿重复添加");
        }
        //初始化Friend对象
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");//没有互相关注

        //查询对方是否已经关注"我"
        if(friendDao.getCountByUserIdAndFriendId(friendid, userid)==1){
            //对方也关注我
            friend.setIslike("1");

            friendDao.updateIsLike(friendid, userid, "1");
        }
        //添加好友记录到好友表
        friendDao.save(friend);

        //对方的粉丝数+1
        userClient.updateFans(friendid, 1);
        //我的关注数+1
        userClient.updateFollow(userid, 1);
    }
    //添加非好友
    public void addNoFriend(String userid,String friendid){
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);

        noFriendDao.save(noFriend);
    }
    //删除好友
    public void deleteFriend(String userid,String friendid){
        if(friendDao.getCountByUserIdAndFriendId(friendid, userid)==1){
        //互相关注 => 维护对方的islike为0
            friendDao.updateIsLike(friendid, userid, "0");
        }
        //1 删除Friend表记录
        friendDao.deleteByUserIdAndFriendId(userid, friendid);
        //2 向NoFriend添加
        addNoFriend(userid, friendid);

        //对方的粉丝数+1
        userClient.updateFans(friendid, -1);
        //我的关注数+1
        userClient.updateFollow(userid, -1);
    }

}
