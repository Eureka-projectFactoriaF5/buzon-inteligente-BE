package com.f5.buzon_inteligente_BE.auth.register;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.f5.buzon_inteligente_BE.profile.ProfileService;
import com.f5.buzon_inteligente_BE.roles.Role;
import com.f5.buzon_inteligente_BE.roles.RoleService;
import com.f5.buzon_inteligente_BE.user.UserRepository;
import com.f5.buzon_inteligente_BE.user.User;


@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ProfileService profileService;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RegisterService registerService;

    @Test
    @DisplayName("Should RegisterUser success")
    void testRegisterUserSuccess() throws Exception, SecurityException {
        RegisterRequest registerRequest =  new RegisterRequest("DNI", "Name", "Surname", "test@example.com",  Base64.getEncoder().encodeToString("Password".getBytes()));
        
        when(userRepository.findByUserEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.findByUserDni("DNI")).thenReturn(Optional.empty());

        Role testRole = new Role("ROLE_USER");
        when(roleService.getDefaultRole()).thenReturn(testRole);
        when(passwordEncoder.encode("Password")).thenReturn(Base64.getEncoder().encodeToString("Password".getBytes()));

        User testUser = new User(
            registerRequest.getUserDni(),
            registerRequest.getUserName(),
            registerRequest.getUserSurname(),
            registerRequest.getUserEmail(),
            registerRequest.getUserPassword(),
            testRole);

        Field field = User.class.getDeclaredField("userId");
        field.setAccessible(true);
        field.set(testUser, 1L);

        when(userRepository.save(any(User.class))).thenReturn(testUser);

        Map<String, String> result = registerService.registerUser(registerRequest);
        
        assertEquals("Success", result.get("message"));
        verify(userRepository).save(any(User.class));
        verify(passwordEncoder).encode("Password");
        verify(roleService).getDefaultRole();
    }
    
}
