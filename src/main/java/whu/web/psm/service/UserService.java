package whu.web.psm.service;

/**
 * 
 * @description: 用户服务模块接口
 * @author	   : xsy
 * @date	   : 2020年5月23日
 */

public interface UserService {
	
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
	 * @return false--账号或密码错误; true--登陆成功
	 */
	boolean login(String phone, String pwd);
	
		
	/**
	 * 
	 * @description: 根据电话号码获取用户信息
	 * @param phone
	 * @return 用户信息
	 */
	User getUserByPhone(String phone);
	
	
	/**
	 * 
	 * @description: 更新用户
	 * @param user--新用户信息
	 * @return
	 */
	boolean updateUser(User user);
}
