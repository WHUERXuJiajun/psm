package whu.web.psm.service;

import org.springframework.data.domain.Page;
import whu.web.psm.pojo.MissionTable;
import java.util.List;
import java.util.Map;

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
    Map<String, Object> getDetails(Integer mid);


    /**
     * 根据标签返回任务
     *
     * @param label -- 标签名
     * @return 任务列表
     */
    Map<String,Object> selectMissionByLabel(String label, Integer page, Integer size);


    /**
     * 根据关键词返回查找的任务
     *
     * @param key -- 关键词
     * @return
     */
    Page<MissionTable> selectMissionByKey(String key, Integer page, Integer size);

    /**
     *
     * @description: 用户撤销任务
     * @param mid
     * @return
     */
    boolean mission_cancel(Integer mid);
    /**
     *
     * @description: 用户更新任务
     * @param mid
     * @param title
     * @param description
     * @param money
     * @param label1
     * @param label2
     * @param label3
     * @param phone
     * @return
     */

    boolean mission_update(Integer mid,String title,String description,double money,String label1,String label2,String label3,String phone);


    /**
     *
     * @description: 根据发布时间排序,并分页
     * @return
     */
    Map<String,Object> selectMissionByTime(Integer page,Integer size);

    /**
     *
     * @description: 根据酬金排序，并分页
     * @return
     */
    Map<String,Object> selectMissionByMoney(Integer page,Integer size);

    /**
     *
     * @description: 根据截止时间排序，并分页
     * @return
     */
    Map<String,Object> selectMissionByDdl(Integer page,Integer size);
}