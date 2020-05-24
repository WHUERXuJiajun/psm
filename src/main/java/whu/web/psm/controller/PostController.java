package whu.web.psm.controller;
import java.util.List;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.service.PostService;
import whu.web.psm.service.MissionTableService;


@RestController
@RequestMapping("/post")
@Api(value = "PostController",tags = "上传模块")
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping(value = "/post_mission")
    @ApiOperation(
            value = "用户上传任务",
            notes = "输入任务标题、任务描述、酬金，电话，返回boolean"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "任务标题", name = "title",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "任务描述", name = "description",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "酬金", name = "money",paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "标签1", name = "label1",paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "标签1", name = "label2",paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "标签2", name = "label3",paramType = "query",dataType = "string"),
    })
    public boolean postMission(@RequestParam("title") String title,
                               @RequestParam("description") String description,
                               @RequestParam("money") String money,
                               @RequestParam("label1") String label1,
                               @RequestParam("label2") String label2,
                               @RequestParam("label3") String label3){
        return postService.insertMissionTable(title,description,money,label1,label2,label3);
    }

    @PostMapping(value = "/post2post")
    @ApiOperation(
            value = "上传任务记录到post表中",
            notes = "输入电话，返回boolean"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "电话", name = "phone",paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "任务id", name = "mid",paramType = "query",dataType = "integer"),
    })
    public boolean post2post(@RequestParam("phone") String phone,
                             @RequestParam("mid") Integer mid){
        return postService.insertPost(phone,mid);
    }
}
