package whu.web.psm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import whu.web.psm.pojo.Collection;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.service.CollectionService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/collect")
@Api(value = "CollectionController",tags = "收藏模块")
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @PreAuthorize("hasRole('user')")
    @GetMapping
    @ApiOperation(
            value = "根据用户电话获取收藏的任务",
            notes = "根据用户电话获取收藏的任务"
    )
    List<MissionTable> getCollectMissionsByPhone(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return collectionService.getCollectMissionsByPhone(principal.getName());
    }

    @PreAuthorize("hasRole('user')")
    @PostMapping
    @ApiOperation(
            value = "收藏任务",
            notes = "收藏任务"
    )
    boolean collectMission(@RequestBody Collection collection) {
        return collectionService.CollectMission(collection);
    }


    @PreAuthorize("hasRole('user')")
    @DeleteMapping
    @ApiOperation(
            value = "取消收藏",
            notes = "取消收藏"
    )
    void deleteCollection(@RequestBody MissionTable missionTable, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        collectionService.deleteCollection(principal.getName(),missionTable.getMid());
    }

}
