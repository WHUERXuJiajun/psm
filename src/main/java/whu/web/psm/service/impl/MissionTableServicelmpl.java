package whu.web.psm.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import whu.web.psm.dao.MissionTableElasticSearchMapper;
import whu.web.psm.dao.MissionTableMapper;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.pojo.MissionTableExample;
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

    @Autowired
    MissionTableElasticSearchMapper missionTableElasticSearchMapper;

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

    @Override
    public Page<MissionTable> selectMissionByKey(String key, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return missionTableElasticSearchMapper.findByTitleOrDescriptionLike(key, key, pageable);
    }

    @Override
    public boolean mission_cancel(Integer mid){
        MissionTable missionTable=new MissionTable();
        try{
            /*根据mid删除条目*/
            missionTableMapper.deleteByPrimaryKey(mid);
            return true;
        }catch(Exception e){
            return  false;
        }
    }

    @Override
    public boolean mission_update(Integer mid,String title,String description,double money,String label1,String label2,String label3,String phone){
        MissionTable missionTable=new MissionTable();
        try{
            /*选择根据mid更新条目*/
            missionTable.setMid(mid);
            missionTable.setState(0);
            missionTable.setTitle(title);
            missionTable.setLabel1(label1);
            missionTable.setLabel2(label2);
            missionTable.setLabel3(label3);
            missionTable.setMoney(money);
            missionTable.setDescription(description);
            missionTableMapper.updateByPrimaryKey(missionTable);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<MissionTable> selectMissionByTime(Integer page,Integer size){
        /*这里采用查询后由后台处理分页事务的方式*/
        List<MissionTable> missionTables=new ArrayList<>();
        MissionTableExample missionTableExample=new MissionTableExample();
        missionTableExample.setOrderByClause("post_time");
        missionTables=missionTableMapper.selectByExample(missionTableExample);
        List<MissionTable> missionTables_show=new ArrayList<>();
        Integer startindex=(page-1)*size;
        Integer i=0;
        while(i<size&&(i+startindex)<missionTables.size()){
            missionTables_show.add(missionTables.get(startindex+i));
            i++;
        }
        return missionTables_show;
    }

    @Override
    public List<MissionTable> selectMissionByMoney(Integer page,Integer size){
        /*这里采用查询后由后台处理分页事务的方式*/
        List<MissionTable> missionTables=new ArrayList<>();
        MissionTableExample missionTableExample=new MissionTableExample();
        missionTableExample.setOrderByClause("money");
        missionTables=missionTableMapper.selectByExample(missionTableExample);
        List<MissionTable> missionTables_show=new ArrayList<>();
        Integer startindex=(page-1)*size;
        Integer i=0;
        while(i<size&&(startindex+i)<missionTables.size()){
            missionTables_show.add(missionTables.get(startindex+i));
            i++;
        }
        return missionTables_show;
    }

    @Override
    public List<MissionTable> selectMissionByDdl(Integer page,Integer size){
        /*这里采用查询后由后台处理分页事务的方式*/
        List<MissionTable> missionTables=new ArrayList<>();
        MissionTableExample missionTableExample=new MissionTableExample();
        missionTableExample.setOrderByClause("end_time");
        missionTables=missionTableMapper.selectByExample(missionTableExample);
        List<MissionTable> missionTables_show=new ArrayList<>();
        Integer startindex=(page-1)*size;
        Integer i=0;
        while(i<size&&(i+startindex)<missionTables.size()){
            missionTables_show.add(missionTables.get(startindex+i));
            i++;
        }
        return missionTables_show;
    }

}
