package com.f5.buzon_inteligente_BE.auth.register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.f5.buzon_inteligente_BE.auth.register.RegisterExceptions.DniAlreadyExistsException;
import com.f5.buzon_inteligente_BE.auth.register.RegisterExceptions.EmailAlreadyExistsException;
import com.f5.buzon_inteligente_BE.auth.register.RegisterExceptions.RegisterException;
import com.f5.buzon_inteligente_BE.profile.ProfileService;
import com.f5.buzon_inteligente_BE.roles.Role;
import com.f5.buzon_inteligente_BE.roles.RoleService;
import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserRepository;

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

    private RegisterRequest registerRequest;
    private Role testRole;
    private String encodedPassword;

    @BeforeEach
    public void setup() {
        
        encodedPassword = Base64.getEncoder().encodeToString("Password".getBytes());
        registerRequest = new RegisterRequest(
            "DNI", 
            "Name",
            "Surname",
            "test@example.com",
            encodedPassword
        );

        testRole = new Role("ROLE_USER");

    }

    @Test
    @DisplayName("Should RegisterUser success")
    void testRegisterUserSuccess() throws Exception, SecurityException {
        
        when(userRepository.findByUserEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.findByUserDni("DNI")).thenReturn(Optional.empty());

        when(roleService.getDefaultRole()).thenReturn(testRole);
        when(passwordEncoder.encode("Password")).thenReturn(encodedPassword);

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

    @Test
    @DisplayName("Should RegisterUser fail because email already exists")
    void testRegisterUserFailEmailAlreadyExists() throws Exception, SecurityException {
        when(userRepository.findByUserEmail("test@example.com")).thenReturn(Optional.of(new User()));

        EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class,
                () -> registerService.registerUser(registerRequest));

        verify(userRepository).findByUserEmail("test@example.com");
        assertEquals("El email ya está registrado", exception.getMessage());
    }

    @Test
    @DisplayName("Should RegisterUser fail because DNI already exists")
    void testRegisterUserFailDniAlreadyExists() throws Exception, SecurityException {
        when(userRepository.findByUserDni("DNI")).thenReturn(Optional.of(new User()));

        DniAlreadyExistsException exception = assertThrows(DniAlreadyExistsException.class,
                () -> registerService.registerUser(registerRequest));

        verify(userRepository).findByUserDni("DNI");
        assertEquals("El DNI ya está registrado", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when password cannot be decoder")
    void testRegisterUserFailPasswordCannotBeDecoder() throws Exception, SecurityException {
        registerRequest.setUserPassword("###Password###");

        when(userRepository.findByUserEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.findByUserDni("DNI")).thenReturn(Optional.empty());

        RegisterException exception = assertThrows(RegisterException.class,
                () -> registerService.registerUser(registerRequest));
                
        assertEquals("Error decoding password from Base64", exception.getMessage());
    }
        
}
