package whu.web.psm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whu.web.psm.service.UserService;

@RestController
@RequestMapping("/user")
@Api(value = "UserController",tags = "用户模块")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @ApiOperation(
            value = "注册",
            notes = "输入电话和密码，进行注册"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "电话", name = "phone",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "密码", name = "pwd",paramType = "query",dataType = "String"),
    })
    public boolean register(@RequestParam("phone") String phone,
                            @RequestParam("pwd") String pwd){
        return userService.register(phone,pwd);
        }
}
