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
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String UrlBase = "/api/comment";
    private int mid = 1;
    String phone = "15387315836";
    @Test
    public void getCommentsByMid() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase+"/" + mid)
                .param("mid", ""+mid)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"user"})
    public void postComment() throws Exception{
        String commentJson = "{\"mid\":\""+ mid + "\",\"from_user\":\""+ phone +"\"" +
                ",\"content\":\"MyControlTest\"}";
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post(UrlBase)
                .content(commentJson).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    @WithMockUser(roles = {"user"})
    public void addThumb() throws Exception{
        String commentJson = "{\"mid\":\""+ mid + "\",\"from_user\":\""+ phone +"\"" +
                ",\"content\":\"MyControlTest\"}, \"thumb\":2";
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.put(UrlBase+"/thumb")
                .content(commentJson).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }
}