package whu.web.psm.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import whu.web.psm.pojo.User;

import java.io.*;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    //测试所需的基本信息，数据库中需存在
    String userName = "17371447819";
    String pwd = "123456";
    String admin = "15387315836";
    String adminPwd = "123456";
    //测试用图片地址
    String path = "C:\\Users\\yuriv\\Pictures\\favicon.jpg";

    /*测试
    * */
    @Test
    public void getUserFromToken() {
        //token为null时
        String token = null;
        assertNull(userService.getUserFromToken(token));

        //正确的token
        token = userService.login(userName, pwd);
        assertNotNull(userService.getUserFromToken(token));
    }

    @Test
    public void register() {
        boolean res;
        //测试注册成功
        String randomUserName = Integer.toString(new Random().nextInt());
        res = userService.register(randomUserName, pwd);
        assertTrue(res);

        res = userService.register(randomUserName, pwd);
        assertFalse(res);
    }

    @Test
    public void login() {
        String res;
        //测试错误登录
        res = userService.login(userName,pwd+'1');
        assertNull(res);
        //测试管理员分支
        res = userService.login(admin, adminPwd);
        assertNotNull(res);
        //测试普通用户
        userService.login(userName, pwd);
        assertNotNull(res);
    }

    @Test
    public void updateUser() {
        boolean res;
        //测试正确的用户
        String token = userService.login(userName, pwd);
        User user = userService.getUserFromToken(token);
        res = userService.updateUser(user);
        assertTrue(res);
        //错误的用户
        user.setPhone(userName+'1');
        res = userService.updateUser(user);
        assertFalse(res);
    }

    @Test
    public void logout() {
        String token = userService.login(userName,pwd);
        userService.logout(token);
    }

    @Test
    public void selectTopByScore() {
        assertNotNull(userService.selectTopByScore(10));
    }

    @Test
    public void updatePwd() {
        boolean res;
        //用户名密码正确
        res = userService.updatePwd(userName, pwd, pwd);
        assertTrue(res);
        //用户名密码错误
        res = userService.updatePwd(userName, pwd+"11", pwd);
        assertFalse(res);
    }

    @Test
    public void uploadIcon() {
        boolean res;
        try {
            File file = new File(path);
            InputStream inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);

            res = userService.uploadIcon(multipartFile, userName);
            assertTrue(res);
            //?怎么才能return false呢

        }catch (FileNotFoundException e){

        }catch (IOException e){

        }
    }
}