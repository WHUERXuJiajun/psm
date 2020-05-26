package whu.web.psm.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whu.web.psm.dao.MissionTableMapper;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.service.MissionTableService;

/**
 *
 * @description: 任务列表模块实现
 * @author	   : hzf
 * @date	   : 2020年5月23日
 */
@Service
public class MissionTableServicelmpl implements MissionTableService {

    @Autowired
    MissionTableMapper missionTableMapper;

    @Override
    public List<MissionTable> getMissions_all(Integer page, Integer size) {
        int offset = 0;
        if(size<=0)
            return null;
        if (page > 0) {
            offset = (page - 1) * size;
        }
        return missionTableMapper.getMissionByPage(size,offset);
    }

    @Override
    public List<MissionTable> getDetails(Integer mid){
        List<MissionTable> missionTable=new ArrayList<>();
            missionTable.add(missionTableMapper.selectByPrimaryKey(mid));
        return missionTable;
    }

    @Override
    public List<MissionTable> selectMissionByLabel(String label, Integer page, Integer size) {
    	int offset = 0;
        if(size<=0)
            return null;
        if (page > 0) {
            offset = (page - 1) * size;
        }
    	return missionTableMapper.selectMissionByLabel(label, size, offset);
    }
}
