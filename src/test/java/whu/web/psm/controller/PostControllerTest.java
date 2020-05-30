package whu.web.psm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String UrlBase = "/api/post";
    String phone = "15387315836";
    Date endTime = new Date();
    @Test
    @WithMockUser(roles = {"user"})
    public void postMission() throws Exception{
        endTime.setTime(200000);

        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post(UrlBase+"/post_mission")
                .param("title", "TestControl").param("description","MyTestController")
                .param("money","1.2").param("label1", "label1")
                .param("label2", "label2").param("label3", "label3")
                .param("phone", phone).param("end_time", endTime.toString())
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertEquals("true", res.getResponse().getContentAsString());
    }
}