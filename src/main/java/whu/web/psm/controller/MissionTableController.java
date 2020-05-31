package whu.web.psm.controller;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.service.MissionTableService;

@RestController
@RequestMapping("api/MissionTable")
@Api(value = "MissionTableController",tags = "任务列表模块")
public class MissionTableController {

    @Autowired
    MissionTableService missionTableService;

    @GetMapping(value = "/getMissions_all")
    @ApiOperation(
            value = "获取任务列表",
            notes = "分页获取数据库中所有信息列表"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "page",paramType = "query",dataType = "integer"),
            @ApiImplicitParam(value = "每页数量", name = "size",paramType = "query",dataType = "integer")
    })
    public List<MissionTable> acceptMission(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                            @RequestParam(value = "size",defaultValue = "8") Integer size){
        return missionTableService.getMissions_all(page,size);
    }

    @GetMapping(value="/getDetails")
    @ApiOperation(
            value = "获取任务详情",
            notes = "根据mid获取任务详情"
    )
    @ApiImplicitParam(value = "任务id", name = "mid",paramType = "query",dataType = "integer")
    public Map<String, Object> getDetails(@RequestParam("mid") Integer mid){
        return missionTableService.getDetails(mid);
    }



    @GetMapping(value="/getMissions_label")
    @ApiOperation(
            value = "根据标签分页获取任务",
            notes = "根据标签分页获取任务"
    )
    @ApiImplicitParams({
       @ApiImplicitParam(value = "标签", name = "label",paramType = "query",dataType = "String"),
       @ApiImplicitParam(value = "页号", name = "page",paramType = "query",dataType = "Integer"),
       @ApiImplicitParam(value = "每页数量", name = "size",paramType = "query",dataType = "Integer")
    })
    public Map<String,Object> getMissionByLabel(@RequestParam(value = "label",defaultValue = "") String label,
									    		@RequestParam(value = "page",defaultValue = "1") Integer page,
									            @RequestParam(value = "size",defaultValue = "8")Integer size){

        return missionTableService.selectMissionByLabel(label, page, size);
    }


    @GetMapping(value="/getMissions_key")
    @ApiOperation(
            value = "根据关键词分页获取任务",
            notes = "根据关键词分页获取任务"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "关键词", name = "key",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "页号", name = "page",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(value = "每页数量", name = "size",paramType = "query",dataType = "Integer")
    })
    public Page<MissionTable> getMissionByKey(@RequestParam("key") String key,
                                                @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                @RequestParam(value = "size",defaultValue = "8")Integer size){
        return missionTableService.selectMissionByKey(key,page,size);
    }

    @GetMapping(value="/mission_cancel")
    @ApiOperation(
            value = "完成任务",
            notes = "根据mid删除任务，并奖励"
    )
    @ApiImplicitParams({

            @ApiImplicitParam(value = "任务id", name = "mid",paramType = "query",dataType = "integer"),

    })
    public Boolean mission_cancel(@RequestParam("mid") Integer mid){
        return missionTableService.mission_cancel(mid);
    }

    @GetMapping(value="/mission_update")
    @ApiOperation(
            value = "更改任务详情",
            notes = "根据mid等更改任务详情"
    )
    @ApiImplicitParams({

            @ApiImplicitParam(value = "任务id", name = "mid",paramType = "query",dataType = "integer"),
            @ApiImplicitParam(value = "任务标题", name = "title",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "任务描述", name = "description",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "酬金", name = "money",paramType = "query",dataType = "double"),
            @ApiImplicitParam(value = "标签1", name = "label1",paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "标签1", name = "label2",paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "标签2", name = "label3",paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "电话", name = "phone",paramType = "query",dataType = "string"),
    })
    public Boolean mission_update(@RequestParam("mid") Integer mid,
                                  @RequestParam("title") String title,
                                  @RequestParam("description") String description,
                                  @RequestParam("money") double money,
                                  @RequestParam("label1") String label1,
                                  @RequestParam("label2") String label2,
                                  @RequestParam("label3") String label3,
                                  @RequestParam("phone") String phone){
        return missionTableService.mission_update(mid,title,description,money,label1,label2,label3,phone);
    }

    @GetMapping(value="/orderMissonsByTime")
    @ApiOperation(
            value = "根据发布时间排序",
            notes = "获取任务，并根据时间排序"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页号", name = "page",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(value = "每页数量", name = "size",paramType = "query",dataType = "Integer")
    })
    public Map<String,Object> orderMissionsByTime(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "size",defaultValue = "8")Integer size){
        return missionTableService.selectMissionByTime(page,size);
    }

    @GetMapping(value="/orderMissionsByMoney")
    @ApiOperation(
            value = "根据酬金排序",
            notes = "获取任务，并根据酬金排序"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页号", name = "page",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(value = "每页数量", name = "size",paramType = "query",dataType = "Integer")
    })
    public Map<String,Object> orderMissionsByMoney(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "size",defaultValue = "8")Integer size){
        return missionTableService.selectMissionByMoney(page,size);
    }


    @GetMapping(value="/orderMissionsByDdl")
    @ApiOperation(
            value = "根据截止时间排序",
            notes = "获取任务，并根据时间排序"
    )
    public Map<String,Object> orderMissionsByDdl(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "size",defaultValue = "8")Integer size){
        return missionTableService.selectMissionByDdl(page,size);
    }
}
