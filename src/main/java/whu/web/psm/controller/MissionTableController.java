package whu.web.psm.controller;

import java.util.List;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MissionTable> acceptMission(@RequestParam("page") Integer page,
                                            @RequestParam(value = "size",defaultValue = "8") Integer size){
        return missionTableService.getMissions_all(page,size);
    }

    @GetMapping(value="/getDetails")
    @ApiOperation(
            value = "获取任务详情",
            notes = "根据mid获取任务详情"
    )
    @ApiImplicitParams({

            @ApiImplicitParam(value = "任务id", name = "mid",paramType = "query",dataType = "integer"),
    })
    public List<MissionTable> getDetails(@RequestParam("mid") Integer mid){
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
    public List<MissionTable> getMissionByLabel(@RequestParam("label") String label,
									    		@RequestParam("page") Integer page,
									            @RequestParam(value = "size",defaultValue = "8")Integer size){
        return missionTableService.selectMissionByLabel(label, page, size);
    }
}
