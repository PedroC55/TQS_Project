package tqs.project.mailMoverPlatform.ControllerTests;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import tqs.project.mailMoverPlatform.Controller.AdminController;
import tqs.project.mailMoverPlatform.Entities.Admin;
import tqs.project.mailMoverPlatform.Entities.LoginInfo;
import tqs.project.mailMoverPlatform.Services.AdminServiceImpl;



@WebMvcTest(AdminController.class)
public class AdminControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AdminServiceImpl adminService;

    @Test
    public void testCreateAdmin() throws Exception {
        Admin newAdmin = new Admin("admin@mail.com", "admin_123");
        newAdmin.setId(1L);

        given(adminService.addAdmin(newAdmin)).willReturn(newAdmin);

        mockMvc.perform(post("/v1/admin/new")
                    .content(asJsonString(newAdmin))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("admin@mail.com"))
                .andExpect(jsonPath("$.password").value("admin_123"));
    }

    @Test
    public void testLogin_Sucessful() throws Exception {
        LoginInfo loginInfo = new LoginInfo("admin@mail.com", "admin_123");

        given(adminService.performLogin("admin@mail.com", "admin_123")).willReturn(true);

        mockMvc.perform(post("/v1/admin/login")
                    .content(asJsonString(loginInfo))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testLogin_Unauthorized() throws Exception {
        LoginInfo loginInfo = new LoginInfo("admin@mail.com", "admin_123");

        given(adminService.performLogin("admin@mail.com", "admin_123")).willReturn(false);

        mockMvc.perform(post("/v1/admin/login")
                    .content(asJsonString(loginInfo))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("false"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
