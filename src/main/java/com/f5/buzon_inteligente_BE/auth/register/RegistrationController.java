package com.f5.buzon_inteligente_BE.auth.register;

import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest) {
        // Verificar si el email ya se encuentra registrado
        if (userService.findByEmail(registrationRequest.getUserEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El email ya está registrado.");
        }
        
        // Crear la entidad User a partir del DTO
        User newUser = new User();
        newUser.setUserDni(registrationRequest.getUserDni());
        newUser.setUserName(registrationRequest.getUserName());
        // Si en el futuro decides almacenar el apellido en la entidad, podrías agregar:
        // newUser.setUserSurname(registrationRequest.getUserSurname());
        newUser.setUserEmail(registrationRequest.getUserEmail());
        // Encriptar la contraseña
        newUser.setUserPassword(passwordEncoder.encode(registrationRequest.getUserPassword()));
        
        // Asignar valores por defecto a relaciones (ejemplo: credentialId y lockerId) si es requerido en el MVP.
        newUser.setCredentialId(0L);
        newUser.setLockerId(0L);
        
        // Guardar al usuario
        userService.save(newUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito");
    }
}