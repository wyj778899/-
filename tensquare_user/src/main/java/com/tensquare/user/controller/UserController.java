package com.tensquare.user.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JwtUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private JwtUtils jwtUtils;

	/**
	 * 删除,解析并校验token,只有管理员才能执行删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		/*//校验token
		//1.获取Authorization头
		String authorization = request.getHeader("Authorization");
		if (StringUtils.isEmpty(authorization)){
		//未获得到=>提示先登录
			throw new  RuntimeException("请先登录");
		}
		//2 截取出token部分
		if(!authorization.startsWith("Bearer ")){
			//截取失败=> 提示重新登录
			throw new  RuntimeException("请重新登录");
		}
		String token = authorization.substring(7);
		//3 解析token
		Claims claims = jwtUtils.parseJwt(token);
		if(claims==null){
		//解析失败=> 提示重新登录
			throw new  RuntimeException("请重新登录");
		}
		//4 判断角色是否为admin
		if(!"admin".equals(claims.get("roles"))){
			//不是admin=>提示没有权限操作
			return new Result(false,StatusCode.ACCESSERROR,"没有权限");
		}*/

		Object claims_admin = request.getAttribute("claims_admin");

		if(claims_admin==null){//没登录或不是管理员
			return new Result(false,StatusCode.ACCESSERROR,"未登录或没有权限");
		}

		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

	//
	/**
	 * 发送验证码短信
	 * @param mobile 手机号
	 */
	@RequestMapping(value="/sendsms/{mobile}",method= RequestMethod.POST)
	public Result sendsms(@PathVariable String mobile ){
		userService.sendsms(mobile);
		return new Result(true,StatusCode.OK,"发送成功");
	}
	@RequestMapping(value="/register/{code}",method= RequestMethod.POST)
	public Result register(@PathVariable String code,@RequestBody User user ){

		userService.regist(code,user);

		return new Result(true,StatusCode.OK,"注册成功");
	}
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public Result login(@RequestBody User user ){
		User dbUser = userService.login(user);

		//登录成功后,签发token
		String token = jwtUtils.generateJwt(dbUser.getId(), dbUser.getNickname(), "user");

		Map map = new HashMap();
		map.put("token", token);
		map.put("name", dbUser.getNickname());
		map.put("avatar", dbUser.getAvatar());

		return new Result(true,StatusCode.OK,"登录成功",map);
	}

	@RequestMapping(value="/follow/{userid}/{count}",method= RequestMethod.PUT)
	public Result updateFollow(@PathVariable String userid,@PathVariable Integer count){
		userService.updateFollow(userid, count);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	@RequestMapping(value="/fans/{userid}/{count}",method= RequestMethod.PUT)
	public Result updateFans(@PathVariable String userid,@PathVariable Integer count){
		userService.updateFans(userid, count);
		return new Result(true,StatusCode.OK,"修改成功");
	}
}
