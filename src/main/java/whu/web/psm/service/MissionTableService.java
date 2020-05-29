package whu.web.psm.service;

import org.springframework.data.domain.Page;
import whu.web.psm.pojo.MissionTable;
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
    List<MissionTable> getMissions_all(Integer page, Integer size);

    /**
     *
     * @description: 获取任务详情
     * @Param mid
     * @return
     */
    List<MissionTable> getDetails(Integer mid);


    /**
     * 根据标签返回任务
     *
     * @param label -- 标签名
     * @return 任务列表
     */
    List<MissionTable> selectMissionByLabel(String label, Integer page, Integer size);


    /**
     * 根据关键词返回查找的任务
     *
     * @param key -- 关键词
     * @return
     */
    Page<MissionTable> selectMissionByKey(String key, Integer page, Integer size);


}