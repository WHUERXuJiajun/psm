package whu.web.psm.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServicelmplTest {

    @Autowired
    private PostServicelmpl postServicelmpl;

    String phone = "17371447819";

    @Test
    public void insertMissionTable() {
        boolean res;
        res = postServicelmpl.insertMissionTable("test", "Mytest", "11",
                "label1","label2", "label3", phone);
        assertTrue(res);
        //信息错误时
        res = postServicelmpl.insertMissionTable("test", "Mytest", "11",
                "label1","label2", "label3", phone+'1');
        assertFalse(res);
    }
}