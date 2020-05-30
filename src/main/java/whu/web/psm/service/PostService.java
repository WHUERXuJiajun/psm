package whu.web.psm.service;

import whu.web.psm.pojo.MissionTable;

import java.util.Date;
import java.util.List;

/**
 *
 * @description: “上传任务"服务模块接口
 * @author	   : hzf
 * @date	   : 2020年5月23日
 */
public interface PostService {
    /**
     *
     * @description: 用户上传任务
     * @param title
     * @param description
     * @param money
     * @param label1
     * @param label2
     * @param label3
     * @param phone
     * @return
     */
    boolean insertMissionTable(String title, String description, double money, String label1, String label2, String label3, String phone, Date end_time);
    /**
     *
     * @description: 上传任务到post表
     * @param phone
     * @param mid
     * @return
     */
    boolean insertPost(String phone,Integer mid);


    /**
     * 根据电话获取发布的任务
     *
     * @param phone
     * @return
     */
    List<MissionTable> getMissionsByPhone(String phone);
}