package whu.web.psm.service.impl;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import whu.web.psm.dao.UserMapper;
import whu.web.psm.pojo.User;
import whu.web.psm.service.UserService;

/**
 * @description: 用户服务模块接口实现
 * @author     : xsy
 * @date     : 2020年5月23日
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${project.upload.path}")
    private String basePath;//文件上传基本路径

    @Autowired
    UserMapper userMapper;

    private Map<String, UserDetails> tokenMap = new HashMap<>();


    @Override
    public UserDetails getUserFromToken(String token) {
        if(token == null) {
            return null;
        }
        return tokenMap.get(token);
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
    public User getUser(String phone) {
        return userMapper.selectByPrimaryKey(phone);
    }

    @Override
    public String login(String phone, String pwd) {
        User user = userMapper.selectByPrimaryKey(phone);//使用电话号码获取实体
        if (user.getPwd().equals(pwd)) {
            UserDetails userDetails;
            if (phone.equals("15387315836")) {
                //管理员
                userDetails = createUser(phone,pwd,new String[]{"manager","user"});
            } else {
                //普通用户
                userDetails = createUser(phone,pwd,new String[]{"user"});
            }
            //创建用户token
            String token = UUID.randomUUID().toString();
            tokenMap.put(token, userDetails);
            return token;
        } else {
            return null;
        }
    }


    @Override
    public boolean updateUser(User user) {
        User user1 = userMapper.selectByPrimaryKey(user.getPhone());
        user1.setMotto(user.getMotto());
        int row = userMapper.updateByPrimaryKey(user1);
        return row == 1;
    }

    public boolean logout(String token) {
        tokenMap.remove(token);
        return true;
    }

    @Override
    public List<User> selectTopByScore(Integer num) {
        return userMapper.selectTopByScore(num);
    }

    @Override
    public boolean updatePwd(String phone, String oldPwd, String newPwd) {
        User user = userMapper.selectByPrimaryKey(phone);//使用电话号码获取实体
        if (user.getPwd().equals(oldPwd)) {
            //密码正确，更新密码
            user.setPwd(newPwd);
            userMapper.updateByPrimaryKeySelective(user);
            return true;
        } else {
            return false;
        }
    }


    /**
     * 上传单个文件
     *
     * @param icon -- 上传的头像
     * @return
     * @author xsy
     */
    public boolean uploadIcon(MultipartFile icon, String phone){
        //文件基本路径 (basePath/{uid)
        String filePath = (basePath + phone)
                .replace("/", java.io.File.separator)
                .replace("\\", java.io.File.separator);
        java.io.File saveFile = new java.io.File(filePath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        try {
            icon.transferTo(saveFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * 直接调用，用于权限管理(封装了uid)
     *
     * @author  xsy
     *
     */
    private UserDetails createUser (String userName, String password, String[]roles){
        return new UserDetails() {

            //private static final long serialVersionUID = 6905138725952656074L;

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Collection<GrantedAuthority> authorities = new ArrayList<>();

//                //这是增加了一种名为query的权限，可以使用 @hasAuthority("query") 来判断
//                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("query");
//                authorities.add(authority);

                //这是增加到xxx角色，可以用hasRole("xxx")来判断；需要注意所有的角色在这里增加时必须以ROLE_前缀，使用时则没有ROLES_前缀
                for (String role : roles) {
                    SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_" + role);
                    authorities.add(sga);
                }
                return authorities;
            }

            @Override
            public String getPassword() {
                return password;
            }

            @Override
            public String getUsername() {
                return userName;
            }


            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

        };
    }
}

