package com.f5.buzon_inteligente_BE.service;

import com.f5.buzon_inteligente_BE.dto.CredentialRequest;
import com.f5.buzon_inteligente_BE.model.User;
import com.f5.buzon_inteligente_BE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

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
