package whu.web.psm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.pojo.User;
import whu.web.psm.service.UserService;

import javax.servlet.http.HttpServletRequest;

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
            value = "获取用户电话",
            notes = "输入token，返回用户的电话"
    )
    public String getUserByPhone(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }


    @PreAuthorize("hasRole('user')")
    @GetMapping(value = "/user_info")
    @ApiOperation(
            value = "获取用户全部信息",
            notes = "输入token，返回用户"
    )
    public User getUser(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        return userService.getUser(principal.getName());
    }
    
    
    @PreAuthorize("hasRole('user')")
    @PutMapping
    @ApiOperation(
            value = "更新用户motto",
            notes = "输入用户，根据用户id更新用户motto"
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

   // @PreAuthorize("hasRole('user')")
    @PutMapping(value = "/updateCredit")
    @ApiOperation(
            value = "用户信誉积分减二",
            notes = "用户信誉积分减二"
    )
    public boolean updateCredit(@RequestBody MissionTable mission){
         return userService.updateCreditByMission(mission);
    }
    
    
    @PreAuthorize("hasRole('user')")
    @GetMapping(value = "/logout")
    @ApiOperation(
            value = "退出登录",
            notes = "输入token，退出登录"
    )
    public boolean updateUser(@RequestParam String token){
        return userService.logout(token);
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


    @PreAuthorize("hasRole('user')")
    @PostMapping(value = "/uploadIcon", headers = "content-type=multipart/form-data")
    @ApiOperation(
            value = "上传头像",
            notes = "上传头像"
    )
    public boolean uploadIcon(@RequestParam(value = "icon") MultipartFile icon,HttpServletRequest request) throws IOException {
        Principal principal = request.getUserPrincipal();
        return userService.uploadIcon(icon, principal.getName());
    }
    
}
