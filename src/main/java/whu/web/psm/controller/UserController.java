package whu.web.psm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import whu.web.psm.pojo.User;
import whu.web.psm.service.UserService;

@RestController
@RequestMapping("api/user")
@Api(value = "UserController",tags = "用户模块")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/register")
    @ApiOperation(
            value = "注册",
            notes = "输入电话和密码，返回boolean"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "电话", name = "phone",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "密码", name = "pwd",paramType = "query",dataType = "String"),
    })
    public boolean register(@RequestParam("phone") String phone,
                            @RequestParam("pwd") String pwd){
        return userService.register(phone,pwd);
    }
    
    

    @GetMapping(value = "/login")
    @ApiOperation(
            value = "登录",
            notes = "输入电话和密码，返回token"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "电话", name = "phone",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "密码", name = "pwd",paramType = "query",dataType = "String")
    })
    public String login(@RequestParam("phone") String phone,
                            @RequestParam("pwd") String pwd){
        return userService.login(phone, pwd);
    }


    @PreAuthorize("hasRole('user')")
    @GetMapping
    @ApiOperation(
            value = "获取用户信息",
            notes = "输入token，返回用户的信息"
    )
    @ApiImplicitParam(value = "电话", name = "phone",paramType = "query",dataType = "String")
    public User getUserByPhone(@RequestParam("phone") String token){
        return userService.getUserFromToken(token);
    }
    
    
    @PreAuthorize("hasRole('user')")
    @PutMapping
    @ApiOperation(
            value = "更新用户",
            notes = "输入用户，根据用户id更新用户信息"
    )
    public boolean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    
    
    
    @PreAuthorize("hasRole('user')")
    @PutMapping(value = "/updatePwd")
    @ApiOperation(
            value = "更新用户密码",
            notes = "输入phone，旧密码，新密码，更新密码"
    )
    public boolean updatePwd(@RequestParam String phone,
				    		@RequestParam String oldPwd,
				    		@RequestParam String newPwd){
        return userService.updatePwd(phone, oldPwd, newPwd);
    }
    
    
    
    @PreAuthorize("hasRole('user')")
    @DeleteMapping(value = "/logout")
    @ApiOperation(
            value = "退出登录",
            notes = "输入token，退出登录"
    )
    @ApiImplicitParam(value = "token", name = "token",paramType = "query",dataType = "String")
    public void updateUser(@RequestParam String token){
        userService.logout(token);
    }
    
    
    
    @GetMapping(value = "/rank")
    @ApiOperation(
            value = "查询前num个分数最高的用户",
            notes = "输入数量num，查询前num个分数最高的用户"
    )
    @ApiImplicitParam(value = "num", name = "num",paramType = "query",dataType = "Integer")
    public List<User> selectTopByScore(@RequestParam Integer num){
        return userService.selectTopByScore(num);
    }



    @PostMapping(value = "/uploadIcon", headers = "content-type=multipart/form-data")
    @ApiOperation(
            value = "上传头像",
            notes = "上传头像"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "电话", name = "phone",paramType = "query",dataType = "String",required = true)
    })
    public boolean uploadIcon(@RequestParam(value = "icon") MultipartFile icon,
                           @RequestParam(value = "phone") String phone) throws IOException {
        return userService.uploadIcon(icon, phone);
    }
    
}
