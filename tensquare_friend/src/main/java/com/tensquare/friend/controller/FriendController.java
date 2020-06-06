package com.tensquare.friend.controller;

import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 添加好友
     * @param friendid 对方用户ID
     * @param type 1：喜欢 0：不喜欢
     * @return
     */
    @RequestMapping(value="/like/{friendid}/{type}",method= RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid , @PathVariable String type){
        //获取登录用户id
        Claims claims=(Claims)request.getAttribute("claims_user");

        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"请先登录");
        }
//如果是喜欢
        if(type.equals("1")){
            friendService.addFriend(claims.getId(), friendid);//添加好友
        }else{
//不喜欢
            friendService.addNoFriend(claims.getId(),friendid);//添加非好友
        }


        return new Result(true, StatusCode.OK, "操作成功");
    }

    /**
     * 删除好友
     * @param friendid
     * @return
     */
    @RequestMapping(value="/{friendid}",method=RequestMethod.DELETE)
    public Result remove(@PathVariable String friendid){
        //取出登录用户信息
        Claims claims=(Claims)request.getAttribute("claims_user");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"无权访问");
        }
        friendService.deleteFriend(claims.getId(), friendid);//删除好友
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
