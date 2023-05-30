package tqs.project.mailMoverPlatform.controllerTests;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import tqs.project.mailMoverPlatform.controller.AcpController;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.LoginInfo;
import tqs.project.mailMoverPlatform.services.AcpServiceImpl;

@WebMvcTest(AcpController.class)
public class AcpControllerMockMvcTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AcpServiceImpl acpService;

    @Disabled
    @Test
    public void testCreateACP() throws Exception {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        acp.setId(1L);

        given(acpService.addACP(acp)).willReturn(acp);

        mockMvc.perform(post("/v1/acp/new")
                .content(asJsonString(acp))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            //.andExpect(jsonPath("$.name", is("Loja ACP")))
            .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testGetAllAcps() throws Exception {
        ACP acp1 = new ACP("Loja ACP 1", "Rua dos correios 1", "lojaAcp1@mail.com", "pw_acp");
        acp1.setId(1L);

        ACP acp2 = new ACP("Loja ACP 2", "Rua dos correios 2", "lojaAcp2@mail.com", "pw_acp");
        acp2.setId(2L);

        List<ACP> allAcps = new ArrayList<>();
        allAcps.add(acp1);
        allAcps.add(acp2);

        given(acpService.getAllAcps()).willReturn(allAcps);

        mockMvc.perform(get("/v1/acp/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").exists());
    }

    @Test
    public void testGetAcpById() throws Exception {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        acp.setId(1L);

        given(acpService.getAcpById(1L)).willReturn(Optional.of(acp));

        mockMvc.perform(get("/v1/acp/{id}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Loja ACP"))
                .andExpect(jsonPath("$.address").value("Rua dos correios"))
                .andExpect(jsonPath("$.email").value("lojaAcp@mail.com"));
    }

    @Test
    public void testGetAcpById_AcpNotFound() throws Exception {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        acp.setId(1L);

        given(acpService.getAcpById(2L)).willReturn(Optional.empty());

        mockMvc.perform(get("/v1/acp/{id}", 2L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testLogin_Successful() throws Exception {
        LoginInfo loginInfo = new LoginInfo("lojaAcp@mail.com", "pw_acp");

        given(acpService.performLogin("lojaAcp@mail.com", "pw_acp")).willReturn(true);

        mockMvc.perform(post("/v1/acp/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginInfo)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testLogin_Unauthorized() throws Exception {
        LoginInfo loginInfo = new LoginInfo("lojaAcp@mail.com", "pw_acp");

        given(acpService.performLogin("lojaAcp@mail.com", "pw_acp")).willReturn(false);

        mockMvc.perform(post("/v1/acp/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginInfo)))
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

