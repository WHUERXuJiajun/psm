package whu.web.psm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MissionTableControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String UrlBase = "/api/MissionTable";
    String phone = "15387315836";
    int page=1, size=1;
    @Test
    public void acceptMission() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase+"/getMissions_all")
                .param("page", ""+ page).param("size", "" + size)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }

    @Test
    public void getDetails() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase+"/getDetails")
                .param("mid", "4")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }

    @Test
    public void getMissionByLabel() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase+"/getMissions_label")
                .param("label", "label1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }

    @Test
    public void getMissionByKey() {

    }

    @Test
    public void mission_cancel() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase+"/mission_cancel")
                .param("mid", "3")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertEquals("true", res.getResponse().getContentAsString());
    }

    @Test
    public void mission_update() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase+"/mission_update")
                .param("title", "TestControl").param("description","MyTestController")
                .param("money","1.2").param("label1", "label1")
                .param("label2", "label2").param("label3", "label3")
                .param("phone", phone).param("mid", "2")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertEquals("true", res.getResponse().getContentAsString());
    }

    @Test
    public void orderMissionsByTime() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase+"/orderMissonsByTime")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }

    @Test
    public void orderMissionsByMoney() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase+"/orderMissionsByMoney")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }

    @Test
    public void orderMissionsByDdl() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase+"/orderMissionsByDdl")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }
}