package whu.web.psm.service;

import whu.web.psm.pojo.MissionTable;
import whu.web.psm.pojo.ReceKey;

import java.util.List;
/**
 *
 * @description: “显示任务"服务模块接口
 * @author	   : hzf
 * @date	   : 2020年5月23日
 */
public interface MissionTableService {
    /**
     *
     * @description: 获取对应页的任务
     * @return
     */
    List<MissionTable> getMissions_all(Integer page);

    /**
     *
     * @description: 获取任务详情
     * @Param mid
     * @return
     */
    List<MissionTable> getDetails(Integer mid);
}