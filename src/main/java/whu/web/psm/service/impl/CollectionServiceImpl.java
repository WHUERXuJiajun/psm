package whu.web.psm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whu.web.psm.dao.CollectionMapper;
import whu.web.psm.dao.MissionTableMapper;
import whu.web.psm.pojo.Collection;
import whu.web.psm.pojo.MissionTable;
import whu.web.psm.service.CollectionService;
import whu.web.psm.util.UUIDUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    MissionTableMapper missionTableMapper;

    @Override
    public List<MissionTable> getCollectMissionsByPhone(String phone) {
        List<Collection> collections = collectionMapper.findByPhone(phone);
        List<MissionTable> missionTables = new ArrayList<>();
        for(Collection collection : collections){
            Integer mid = collection.getMid();
            MissionTable missionTable = missionTableMapper.selectByPrimaryKey(mid);
            missionTables.add(missionTable);
        }
        return missionTables;
    }

    @Override
    public boolean CollectMission(Collection collection) {
        List<Collection> collections = collectionMapper.findByPhoneAndMid(collection.getPhone(),collection.getMid());
        if(collections.size()>0)
            return false;
        collection.setId(UUIDUtil.getId());
        collectionMapper.save(collection);
        return true;
    }

    @Transactional
    @Override
    public void deleteCollection(String phone, Integer mid) {
        collectionMapper.deleteByPhoneAndMid(phone,mid);
    }


}
