package com.f5.buzon_inteligente_BE.auth.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.f5.buzon_inteligente_BE.security.JwtUtils;

@WebMvcTest(LoginController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoginService loginService;

    @MockitoBean
    private JwtUtils jwtUtils;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("Should login user")
    void testLogin() throws Exception {
        LoginRequestDto requestDto = new LoginRequestDto("email", "password");
        LoginResponseDto responseDto = new LoginResponseDto("token", "USER", "Login exitoso");
        when(loginService.authenticate(any(LoginRequestDto.class))).thenReturn(responseDto);

        String json = objectMapper.writeValueAsString(requestDto);

        MockHttpServletResponse response = mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(),containsString("Login exitoso"));
        assertThat(response.getContentAsString(),containsString("token"));
        assertThat(response.getContentAsString(),containsString("USER"));
    }

    @Test
    @DisplayName("Should not login user")
    void testLoginFail() throws Exception {
        LoginRequestDto requestDto = new LoginRequestDto("email", "password");
        when(loginService.authenticate(any(LoginRequestDto.class)))
        .thenThrow(new BadCredentialsException("Not authenticate user"));

        String json = objectMapper.writeValueAsString(requestDto);

        MockHttpServletResponse response = mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isUnauthorized())
            .andReturn()
            .getResponse();

        assertThat(response.getStatus(), is(401));
        assertThat(response.getContentAsString(),containsString("Credenciales inválidas"));
    }

    @Test
    @DisplayName("Should not base64 password")
    void testLoginPasswordNotBase64() throws Exception {
        LoginRequestDto requestDto = new LoginRequestDto("email", "####");

        when(loginService.authenticate(any(LoginRequestDto.class)))
        .thenThrow(new IllegalArgumentException("Password mal codificado"));
        String json = objectMapper.writeValueAsString(requestDto);

        MockHttpServletResponse response = mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse();

        assertThat(response.getStatus(), is(400));
        assertThat(response.getContentAsString(),containsString("Formato de password inválido"));
    }
}
