package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {

    @Query("select count(f) from Friend f where userid=?1 and friendid=?2 ")
    Integer getCountByUserIdAndFriendId(String userid,String friendid);

    @Modifying
    @Query("update Friend set islike=?3 where userid=?1 and friendid=?2 ")
    void updateIsLike(String userid,String friendid,String islike);

    @Modifying
    @Query("delete from Friend  where userid=?1 and friendid=?2 ")
    void deleteByUserIdAndFriendId(String userid,String friendid);

}
