package whu.web.psm.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whu.web.psm.dao.MissionTableMapper;
import whu.web.psm.dao.ReceMapper;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.pojo.ReceKey;
import whu.web.psm.service.MissionTableService;
import whu.web.psm.service.ReceService;

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
    public List<MissionTable> getMissions_all(Integer page) {
        List<MissionTable> missionTables = new ArrayList<>();
        Integer mids=page*10;
        while(mids<=mids+10) {
            try {
                missionTables.add(missionTableMapper.selectByPrimaryKey(mids));
            }catch (Exception e) {
            }
            mids++;
        }
        return missionTables;
    }

    @Override
    public List<MissionTable> getDetails(Integer mid){
        List<MissionTable> missionTable=new ArrayList<>();
            missionTable.add(missionTableMapper.selectByPrimaryKey(mid));
        return missionTable;
    }
}
