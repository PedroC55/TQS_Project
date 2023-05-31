package tqs.project.mailMoverPlatform.ControllerTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import tqs.project.mailMoverPlatform.controller.AdminController;
import tqs.project.mailMoverPlatform.entities.Admin;
import tqs.project.mailMoverPlatform.entities.LoginInfo;
import tqs.project.mailMoverPlatform.services.AdminServiceImpl;



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

        given(adminService.addAdmin(any())).willReturn(newAdmin);

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
                .andExpect(content().string("true"));
    }

    @Test
    public void testLogin_Unauthorized() throws Exception {
        LoginInfo loginInfo = new LoginInfo("admin@mail.com", "admin_123");

        given(adminService.performLogin("admin@mail.com", "admin_123")).willReturn(false);

        mockMvc.perform(post("/v1/admin/login")
                    .content(asJsonString(loginInfo))
                    .contentType(MediaType.APPLICATION_JSON))
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
