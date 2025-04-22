package com.f5.buzon_inteligente_BE.auth.register;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.f5.buzon_inteligente_BE.security.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(RegisterController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class RegisterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RegisterService registerService;

    @MockitoBean
    private JwtUtils jwtUtils;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Should registerUser success")
    public void shouldRegisterUserSuccess() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("12345678", "name", "surname", "email@example.com", "password");
        
        when(registerService.registerUser(any(RegisterRequest.class)))
                            .thenReturn(Map.of("message", "Success"));

        String json=objectMapper.writeValueAsString(registerRequest);

        MockHttpServletResponse response = mockMvc.perform(post("/api/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)) 
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse();

        assertThat(response.getStatus(), is(201));
        assertThat(response.getContentType(), is("application/json"));
        assertThat(response.getContentAsString(), is("{\"message\":\"Success\"}"));
    }
}
