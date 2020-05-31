package whu.web.psm.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import whu.web.psm.pojo.ReceKey;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceServiceImplTest {

    @Autowired
    private ReceServiceImpl receService;

    String phone = "17371447819";
    int mid = 20;

    @Test
    public void insertRece() {
        boolean res;
        ReceKey receKey = new ReceKey();
        receKey.setPhone(phone);
        receKey.setMid(mid);
        res = receService.insertRece(receKey);
        assertTrue(res);
        //任务id不存在时
        receKey.setMid(10000);
        res = receService.insertRece(receKey);
        assertFalse(res);
    }

    @Test
    public void getMissionsByPhone() {
        assertNotNull(receService.getMissionsByPhone(phone));
    }

    @Test
    public void cancelMission() {
        boolean res;
        ReceKey receKey = new ReceKey();
        receKey.setPhone(phone);
        receKey.setMid(20);
        res = receService.cancelMission(receKey);
        assertTrue(res);
        res = receService.cancelMission(receKey);
        assertFalse(res);
    }
}