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

    @Test
    public void getMissions_all() {
        //size=0
        assertNull(missionTableServicelmpl.getMissions_all(1, 0));
        assertNotNull(missionTableServicelmpl.getMissions_all(1, 1));
    }

    @Test
    public void getDetails() {
        assertNotNull(missionTableServicelmpl.getDetails(1));
    }

    @Test
    public void selectMissionByLabel() {
        assertNull(missionTableServicelmpl.selectMissionByLabel("宠物", 1, 0));
        assertNotNull(missionTableServicelmpl.selectMissionByLabel("宠物", 1, 1));
    }

}