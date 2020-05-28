package whu.web.psm.service;


import whu.web.psm.pojo.Collection;
import whu.web.psm.pojo.MissionTable;

import java.util.List;

public interface CollectionService {

    /**
     * 根据用户电话查询所有收藏的任务
     *
     * @param phone -- 用户电话
     * @return
     */
    List<MissionTable> getCollectMissionsByPhone(String phone);

    /**
     * 收藏任务
     * @param collection -- 收藏
     */
    void CollectMission(Collection collection);

    /**
     * 删除收藏
     *
     * @param collection -- 收藏
     */
    void deleteCollection(Collection collection);
}
