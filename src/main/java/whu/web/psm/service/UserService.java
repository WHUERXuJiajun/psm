package whu.web.psm.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import whu.web.psm.pojo.User;

/**
 * 
 * @description: 用户服务模块接口
 * @author	   : xsy
 * @date	   : 2020年5月23日
 */

public interface UserService {


	UserDetails getUserFromToken(String token);

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
	 * 
	 * @description: 更新密码
	 * @param phone -- 账户电话
	 * @param oldPwd -- 旧密码
	 * @param newPwd -- 新密码
	 * @return
	 */
	boolean updatePwd(String phone, String oldPwd, String newPwd);
	
	
	
	
	/**
	 * 退出登录
	 */
	boolean logout(String token);
	
	
	
    /**
     * 
     * @description: 查询前num个分数最高的用户
     * @param num -- 前num个
     * @return
     */
	List<User> selectTopByScore(Integer num);


	/**
	 * 用户上传头像
	 *
	 * @param icon -- 用户头像
	 * @param phone -- 用户电话
	 */
	boolean uploadIcon(MultipartFile icon, String phone) throws IOException;
}
