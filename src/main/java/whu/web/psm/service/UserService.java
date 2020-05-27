package whu.web.psm.service;

import java.util.List;

import whu.web.psm.pojo.User;

/**
 * 
 * @description: 用户服务模块接口
 * @author	   : xsy
 * @date	   : 2020年5月23日
 */

public interface UserService {


	/**
	 * 根据token获取用户信息
	 *
	 * @param token
	 * @return
	 */
	User getUserFromToken(String token);

	/**
	 * 
	 * @description: 注册用户
	 * @param phone
	 * @param pwd
	 * @return false--用户已存在; true--注册成功
	 */
	boolean register(String phone, String pwd);
	
	
	/**
	 * 
	 * @description: 登陆账户
	 * @param phone
	 * @param pwd
	 * @return 用户token
	 */
	String login(String phone, String pwd);
	
	
	/**
	 * 
	 * @description: 更新用户
	 * @param user--新用户信息
	 * @return
	 */
	boolean updateUser(User user);


	/**
	 * 退出登录
	 */
	void logout(String token);
	
	
	
    /**
     * 
     * @description: 查询前num个分数最高的用户
     * @param num -- 前num个
     * @return
     */
	List<User> selectTopByScore(Integer num);
}
