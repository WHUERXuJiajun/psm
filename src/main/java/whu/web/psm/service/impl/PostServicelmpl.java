package whu.web.psm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whu.web.psm.dao.MissionTableMapper;
import whu.web.psm.dao.PostMapper;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.pojo.PostKey;
import whu.web.psm.service.PostService;
/**
 *
 * @description: 上传任务模块接口实现
 * @author	   : hzf
 * @date	   : 2020年5月23日
 */
@Service
public class PostServicelmpl implements PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    MissionTableMapper missionTableMapper;

    @Override
    public boolean insertPost(String phone,Integer mid) {
        try {
            PostKey postKey=new PostKey();
            postKey.setMid(mid);
            postKey.setPhone(phone);
            postMapper.insert(postKey);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<MissionTable> getMissionsByPhone(String phone) {
        List<Integer> mids = postMapper.selectMidByPhone(phone);
        List<MissionTable> missionTables = new ArrayList<>();
        for (int mid : mids){
            missionTables.add(missionTableMapper.selectByPrimaryKey(mid));
        }
        return missionTables;
    }

    @Override
    public boolean insertMissionTable(String title,String description,double money,String label1,String label2,String label3,String phone,Date end_time) {
        try {
            MissionTable missionTable=new MissionTable();
            missionTable.setState(0);
            missionTable.setTitle(title);
            missionTable.setLabel1(label1);
            missionTable.setLabel2(label2);
            missionTable.setLabel3(label3);
            missionTable.setMoney(money);
            missionTable.setEndTime(end_time);
            missionTable.setDescription(description);
            Date date=new Date();
            missionTable.setPostTime(date);
            missionTableMapper.insert(missionTable);
            int mid = missionTableMapper.getNewId();
            insertPost(phone,mid);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
