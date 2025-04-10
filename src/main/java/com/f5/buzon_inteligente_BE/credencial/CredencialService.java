package com.f5.buzon_inteligente_BE.credencial;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.f5.buzon_inteligente_BE.user.User;

import com.f5.buzon_inteligente_BE.user.UserRepository;
@Service
public class CredencialService {
    
    @Autowired
    private UserRepository userRepository;

    public boolean updatePermanentCredential(Long userId, CredentialRequest request) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }
        
        User user = userOptional.get();
        
        if (!user.getUserPassword().equals(request.getCurrentPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        
        Optional<User> existingCredential = userRepository.findByPermanentCredential(request.getNewCredential());
        if (existingCredential.isPresent()) {
            throw new RuntimeException("Credential is already in use");
        }
        
        user.setPermanentCredential(request.getNewCredential());
        userRepository.save(user);

        return true; 
    }

}
