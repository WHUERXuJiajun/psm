package whu.web.psm.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionTableServicelmplTest {

    @Autowired
    private MissionTableServicelmpl missionTableServicelmpl;

    String phone = "17371447819";

    @Test
    public void getMissions_all() {
        //size=0
        assertNull(missionTableServicelmpl.getMissions_all(1, 0));
        assertNotNull(missionTableServicelmpl.getMissions_all(1, 1));
    }

    @Test
    public void getDetails() {
        assertNotNull(missionTableServicelmpl.getDetails(4));
    }

    @Test
    public void selectMissionByLabel() {
        assertNull(missionTableServicelmpl.selectMissionByLabel("宠物", 1, 0));
        assertNotNull(missionTableServicelmpl.selectMissionByLabel("宠物", 1, 1));
    }

    @Test
    public void mission_cancel() {
        boolean res;
        res = missionTableServicelmpl.mission_cancel(1);
        assertTrue(res);
    }

    @Test
    public void mission_update() {
        boolean res;
        res = missionTableServicelmpl.mission_update(2, "testTitle", "MyTestUpdate",
                1.1, "label1", "label2", "label3", phone);
        assertTrue(res);
    }

    @Test
    public void selectMissionByTime() {
        assertNotNull(missionTableServicelmpl.selectMissionByTime(1,1));
    }

    @Test
    public void selectMissionByMoney() {
        assertNotNull(missionTableServicelmpl.selectMissionByMoney(1,1));
    }

    @Test
    public void selectMissionByDdl() {
        assertNotNull(missionTableServicelmpl.selectMissionByDdl(1,1));
    }
}