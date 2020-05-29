package whu.web.psm.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import whu.web.psm.pojo.Collection;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionServiceImplTest {

    @Autowired
    private CollectionServiceImpl collectionService;
    String phone = "15387315836";
    Collection collection;

    public CollectionServiceImplTest(){
        collection = new Collection();
        collection.setMid(1);
        collection.setPhone(phone);
    }
    @Test
    public void getCollectMissionsByPhone() {
        assertNotNull(collectionService.getCollectMissionsByPhone(phone));
    }

    @Test
    public void collectMission() {

        collectionService.CollectMission(collection);
    }

    @Test
    public void deleteCollection() {
        collectionService.deleteCollection(collection);
    }
}