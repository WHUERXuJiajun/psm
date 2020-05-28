package whu.web.psm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whu.web.psm.pojo.Collection;
import whu.web.psm.pojo.Comment;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.service.CollectionService;

import java.util.List;

@RestController
@RequestMapping("api/collect")
@Api(value = "CollectionController",tags = "收藏模块")
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @GetMapping
    @ApiOperation(
            value = "根据用户电话获取收藏的任务",
            notes = "根据用户电话获取收藏的任务"
    )
    List<MissionTable> getCollectMissionsByPhone(@RequestParam String phone) {
        return collectionService.getCollectMissionsByPhone(phone);
    }

    @PostMapping
    @ApiOperation(
            value = "收藏任务",
            notes = "收藏任务"
    )
    void collectMission(@RequestBody Collection collection) {
        collectionService.CollectMission(collection);
    }

}
