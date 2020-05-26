package whu.web.psm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserMapper userMapper;

	

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

	@Override
	public User getUserByPhone(String phone) {
		User user = userMapper.selectByPrimaryKey(phone);
		user.setPwd("");
		return user;
	}

	
	@Override
	public boolean updateUser(User user) {
		int row = userMapper.updateByExampleSelective(user, null);//被影响的行数
		if(row==1)
			return true;
		else 
			return false;
	}
	
	
	/**
     * 根据用户名获取认证用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(phone)) {
            throw new UsernameNotFoundException("UserDetailsService没有接收到用户账号");
        } else {
            /**
             * 根据用户名查找用户信息
             */
            User user = userMapper.selectByPrimaryKey(phone);
            if(user == null) {
                throw new UsernameNotFoundException(String.format("用户'%s'不存在", phone));
            }
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if(user.getPhone().equals("15387315836")) {
                //赋予此账号管理员权限
                grantedAuthorities.add(new SimpleGrantedAuthority("manager"));
                grantedAuthorities.add(new SimpleGrantedAuthority("user"));
            }
            else {
            	//赋予此账号普通用户权限
            	grantedAuthorities.add(new SimpleGrantedAuthority("user"));
			}
            /**
             * 创建一个用于认证的用户对象并返回，包括：用户名，密码，角色
             */
            return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPwd(), grantedAuthorities);
        }
    }

}
