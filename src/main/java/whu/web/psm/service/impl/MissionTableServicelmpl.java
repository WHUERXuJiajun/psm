package whu.web.psm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import whu.web.psm.dao.MissionTableElasticSearchMapper;
import whu.web.psm.dao.MissionTableMapper;
import whu.web.psm.dao.PostMapper;
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

    @Autowired
    PostMapper postMapper;

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
    public Map<String, Object> getDetails(Integer mid){
         MissionTable missionTable = missionTableMapper.selectByPrimaryKey(mid);
         String phone = postMapper.selectPhoneByMid(missionTable.getMid());
         Map<String, Object> ans = new HashMap<>();
         ans.put("mission",missionTable);
         ans.put("phone",phone);
         return ans;
    }

    @Override
    public Map<String,Object> selectMissionByLabel(String label, Integer page, Integer size) {
    	Map<String,Object> map = new HashMap<>();

        int offset = 0;
        if(size<=0)
            return null;
        if (page > 0) {
            offset = (page - 1) * size;
        }
        List<MissionTable> missionTables = missionTableMapper.selectMissionByLabel(label, size, offset);
        if(missionTables.size()%size==0){
            map.put("pageNum",missionTables.size()/size);
        }
        else{
            map.put("pageNum",(missionTables.size()/size)+1);
        }
        List<MissionTable> missionTables_show=new ArrayList<>();
        Integer startindex=(page-1)*size;
        Integer i=0;
        while(i<size&&(i+startindex)<missionTables.size()){
            missionTables_show.add(missionTables.get(startindex+i));
            i++;
        }
        map.put("data",missionTables_show);
        return map;

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
    public Map<String,Object>  selectMissionByTime(Integer page,Integer size){
        /*这里采用查询后由后台处理分页事务的方式*/
        Map<String,Object> map = new HashMap<>();
        List<MissionTable> missionTables=new ArrayList<>();
        MissionTableExample missionTableExample=new MissionTableExample();
        missionTableExample.setOrderByClause("post_time");
        missionTables=missionTableMapper.selectByExample(missionTableExample);
        if(missionTables.size()%size==0){
            map.put("pageNum",missionTables.size()/size);
        }
        else{
            map.put("pageNum",(missionTables.size()/size)+1);
        }
        List<MissionTable> missionTables_show=new ArrayList<>();
        Integer startindex=(page-1)*size;
        Integer i=0;
        while(i<size&&(i+startindex)<missionTables.size()){
            missionTables_show.add(missionTables.get(startindex+i));
            i++;
        }
        map.put("data",missionTables_show);
        return map;
    }

    @Override
    public Map<String,Object> selectMissionByMoney(Integer page,Integer size){
        /*这里采用查询后由后台处理分页事务的方式*/
        Map<String,Object> map = new HashMap<>();
        List<MissionTable> missionTables=new ArrayList<>();
        MissionTableExample missionTableExample=new MissionTableExample();
        missionTableExample.setOrderByClause("money");
        missionTables=missionTableMapper.selectByExample(missionTableExample);
        if(missionTables.size()%size==0){
            map.put("pageNum",missionTables.size()/size);
        }
        else{
        map.put("pageNum",(missionTables.size()/size)+1);
        }
        List<MissionTable> missionTables_show=new ArrayList<>();
        Integer startindex=(page-1)*size;
        Integer i=0;
        while(i<size&&(startindex+i)<missionTables.size()){
            missionTables_show.add(missionTables.get(startindex+i));
            i++;
        }
        map.put("data",missionTables_show);
        return map;
    }

    @Override
    public Map<String,Object> selectMissionByDdl(Integer page,Integer size){
        /*这里采用查询后由后台处理分页事务的方式*/
        Map<String,Object> map = new HashMap<>();
        List<MissionTable> missionTables=new ArrayList<>();
        MissionTableExample missionTableExample=new MissionTableExample();
        missionTableExample.setOrderByClause("end_time");
        missionTables=missionTableMapper.selectByExample(missionTableExample);
        if(missionTables.size()%size==0){
            map.put("pageNum",missionTables.size()/size);
        }
        else{
            map.put("pageNum",(missionTables.size()/size)+1);
        }
        List<MissionTable> missionTables_show=new ArrayList<>();
        Integer startindex=(page-1)*size;
        Integer i=0;
        while(i<size&&(i+startindex)<missionTables.size()){
            missionTables_show.add(missionTables.get(startindex+i));
            i++;
        }
        map.put("data",missionTables_show);
        return map;
    }

}
