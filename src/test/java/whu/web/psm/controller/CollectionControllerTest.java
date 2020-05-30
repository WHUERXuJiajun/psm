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

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CollectionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String UrlBase = "/api/collect";
    String phone = "15387315836";

    @Test
    @WithMockUser(roles = {"user"})
    public void getCollectMissionsByPhone() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase)
                .param("phone", phone)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"user"})
    public void collectMission() throws Exception{
        String collectMissions = "{\"phone\":\""+ phone + "\",\"mid\": 1}";
        mockMvc.perform(MockMvcRequestBuilders.post(UrlBase)
                .content(collectMissions).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(roles = {"user"})
    public void deleteCollection() throws Exception{
        String collectMissions = "{\"phone\":\""+ phone + "\",\"mid\": 1}";
        mockMvc.perform(MockMvcRequestBuilders.delete(UrlBase)
                .content(collectMissions).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}