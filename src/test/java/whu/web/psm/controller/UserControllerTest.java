package whu.web.psm.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import whu.web.psm.pojo.User;

import javax.management.relation.Role;

import java.io.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private WebApplicationContext userContext;
    @Autowired
    private MockMvc mockMvc;

    private String UrlBase = "/api/user";
    private String phone = "173737";
    private String pwd = "123456";

    @Test
    public void register() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post(UrlBase + "/register")
                .param("phone", phone).param("pwd", pwd)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andReturn();
        String content = res.getResponse().getContentAsString();
        assertEquals("true", content);
    }

    @Test
    public void login() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase + "/login")
                .param("phone", phone).param("pwd", pwd)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }

    //不会测
    @Test
    @WithMockUser(roles = {"user"})
    public void getUserByPhone() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"user"})
    public void updateUser() throws Exception {
        String json = "{\"phone\":\"" + phone + "\",\"pwd\":\"" + pwd + "\"}";
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.put(UrlBase)
                .content(json).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertEquals("true", res.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"user"})
    public void updatePwd() throws Exception {
        String json = "{\"phone\":\""+ phone + "\",\"oldPwd\":\"" + pwd + "\",\"newPwd\":\"" + pwd + "\"}";
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.put(UrlBase + "/updatePwd")
                .param("phone", "15387315836").param("oldPwd", pwd).param("newPwd", pwd)
                //.content(json).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertEquals("true", res.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"user"})
    public void logout() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase + "/login")
                .param("phone", "15387315836").param("pwd", pwd)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String token = res.getResponse().getContentAsString();
        res = mockMvc.perform(MockMvcRequestBuilders.delete(UrlBase + "/logout")
                .param("token", token)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    @WithMockUser(roles = {"user"})
    public void selectTopByScore() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get(UrlBase + "/rank")
                .param("num", "2")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertNotNull(res.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"user"})
    public void uploadIcon() throws Exception {
        //测试用图片地址
        String path = "C:\\Users\\yuriv\\Pictures\\favicon.jpg";
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.fileUpload(UrlBase + "/uploadIcon")
                .file("icon", multipartFile.getBytes())
                .param("phone", phone).contentType(MediaType.MULTIPART_FORM_DATA)

                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        assertEquals("true", res.getResponse().getContentAsString());
    }
}