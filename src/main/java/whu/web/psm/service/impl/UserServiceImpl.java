package whu.web.psm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import whu.web.psm.dao.UserMapper;
import whu.web.psm.pojo.User;
import whu.web.psm.service.UserService;

/**
 * 
 * @description: 用户服务模块接口实现
 * @author	   : xsy
 * @date	   : 2020年5月23日
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public boolean register(String phone, String pwd) {
		try {
			User user = new User();
			user.setPhone(phone);
			user.setPwd(pwd);
			user.setCredit(100.0);//信誉分默认100
			userMapper.insert(user);
			return true;
		} catch (DuplicateKeyException e) {
			//重复主键，电话号码则重复
			return false;
		}
	}

	@Override
	public boolean login(String phone, String pwd) {
		User user = userMapper.selectByPrimaryKey(phone);//使用电话号码获取实体
		if(user==null)
			//电话号码错误
			return false;
		if(!pwd.equals(user.getPwd()))
			//密码错误
			return false;
		return true;
	}

}
