package whu.web.psm.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.pojo.ReceKey;
import whu.web.psm.service.ReceService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/Rece")
@Api(value = "ReceController",tags = "用户接收任务模块")
public class ReceController {
	
	@Autowired
	ReceService receService;
	
	@PreAuthorize("hasRole('user')")
	@PostMapping(value = "/accept")
    @ApiOperation(
            value = "用户接受任务",
            notes = "输入电话和任务id，返回boolean"
    )
    public boolean acceptMission(@RequestBody ReceKey receKey){
        return receService.insertRece(receKey);
    }
	
	
	
	@PreAuthorize("hasRole('user')")
	@GetMapping
    @ApiOperation(
            value = "根据用户的电话，获取用户接受的任务",
            notes = "根据用户的电话，获取用户接受的任务"
    )
    public List<MissionTable> acceptMission(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        return receService.getMissionsByPhone(principal.getName());
    }
	
	
	
	@PreAuthorize("hasRole('user')")
	@DeleteMapping
	@ApiOperation(
            value = "取消接收的任务",
            notes = "取消接收的任务"
    )
    public boolean cancelMission(@RequestBody MissionTable missionTable,HttpServletRequest request){
	    Principal principal = request.getUserPrincipal();
	    ReceKey receKey = new ReceKey();
	    receKey.setMid(missionTable.getMid());
	    receKey.setPhone(principal.getName());
        return receService.cancelMission(receKey);
    }
	
}
