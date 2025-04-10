package com.f5.buzon_inteligente_BE.auth.register;


import com.f5.buzon_inteligente_BE.auth.register.RegistrationExceptions.EmailAlreadyExistsException;
import com.f5.buzon_inteligente_BE.auth.register.RegistrationExceptions.RegistrationException;
import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, String> registerUser(RegistrationRequest request) {
        
        if (userRepository.findByUserEmail(request.getUserEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("El email ya está registrado");
        }

      
        String decodedPassword;
        try {
            Decoder decoder = Base64.getDecoder();
            byte[] decodedBytes = decoder.decode(request.getUserPassword());
            decodedPassword = new String(decodedBytes);
        } catch (Exception e) {
            throw new RegistrationException("Error decoding password from Base64", e);
        }

        
        String passwordEncoded = passwordEncoder.encode(decodedPassword);

      
        User newUser = new User(
                request.getUserDni(),
                request.getUserName(),
                request.getUserEmail(),
                passwordEncoded,
                0L, 
                0L  
        );

   
        userRepository.save(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Success");
        return response;
    }
}
