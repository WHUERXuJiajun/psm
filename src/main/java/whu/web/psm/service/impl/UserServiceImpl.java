package whu.web.psm.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;

	private Map<String, UserDetails> tokenMap = new HashMap<>();

	@Cacheable("user")
	public User getUserFromToken(String token) {
		if(token == null) {
			return null;
		}
		String phone = tokenMap.get(token).getPassword();
		return userMapper.selectByPrimaryKey(phone);
	}

	@Override
	public boolean register(String phone, String pwd) {
		try {
			User user = new User();
			user.setPhone(phone);
			user.setPwd(pwd);
			user.setCredit(100.0);//信誉分默认100
			user.setScore(0.0);//积分默认0
			userMapper.insert(user);
			return true;
		} catch (DuplicateKeyException e) {
			//重复主键，电话号码则重复
			return false;
		}
	}

	@Override
	public String login(String phone, String pwd) {
		User user = userMapper.selectByPrimaryKey(phone);//使用电话号码获取实体
		if(user.getPwd().equals(pwd)){
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			if(user.getPhone().equals("15387315836")) {
				//赋予此账号管理员权限
				grantedAuthorities.add(new SimpleGrantedAuthority("manager"));
			}
			grantedAuthorities.add(new SimpleGrantedAuthority("user"));
			String token = UUID.randomUUID().toString();
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPwd(), grantedAuthorities);
			tokenMap.put(token, userDetails);
			return token;
		}else {
			return null;
		}
	}

	
	@Override
	public boolean updateUser(User user) {
		int row = userMapper.updateByExampleSelective(user, null);//被影响的行数
		return row == 1;
	}

	public void logout(String token) {
		tokenMap.remove(token);
	}

	@Override
	public List<User> selectTopByScore(Integer num) {
		return userMapper.selectTopByScore(num);
	}

	@Override
	public boolean updatePwd(String phone, String oldPwd, String newPwd) {
		User user = userMapper.selectByPrimaryKey(phone);//使用电话号码获取实体
		if(user.getPwd().equals(oldPwd)){
			//密码正确，更新密码
			user.setPwd(newPwd);
			userMapper.updateByExample(user, null);
			return true;
		}
		else {
			return false;
		}
	}


}
