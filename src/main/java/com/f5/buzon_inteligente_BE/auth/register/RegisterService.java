package com.f5.buzon_inteligente_BE.auth.register;

import com.f5.buzon_inteligente_BE.auth.register.RegisterExceptions.DniAlreadyExistsException;
import com.f5.buzon_inteligente_BE.auth.register.RegisterExceptions.EmailAlreadyExistsException;
import com.f5.buzon_inteligente_BE.auth.register.RegisterExceptions.RegisterException;
import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserRepository;
import com.f5.buzon_inteligente_BE.profile.ProfileService;
import com.f5.buzon_inteligente_BE.roles.Role;
import com.f5.buzon_inteligente_BE.roles.RoleService;
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
    private final ProfileService profileService;
    private final RoleService roleService;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            ProfileService profileService, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileService = profileService;
        this.roleService = roleService;
    }

    public Map<String, String> registerUser(RegisterRequest request) {

        if (userRepository.findByUserEmail(request.getUserEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("El email ya está registrado");
        }

        if (userRepository.findByUserDni(request.getUserDni()).isPresent()) {
            throw new DniAlreadyExistsException("El DNI ya está registrado");
        }

        String decodedPassword;
        try {
            Decoder decoder = Base64.getDecoder();
            byte[] decodedBytes = decoder.decode(request.getUserPassword());
            decodedPassword = new String(decodedBytes);
        } catch (Exception e) {
            throw new RegisterException("Error decoding password from Base64", e);
        }

        String passwordEncoded = passwordEncoder.encode(decodedPassword);

        Role defaultRole = roleService.getDefaultRole();

        User newUser = new User(
                request.getUserDni(),
                request.getUserName(),
                request.getUserSurname(),
                request.getUserEmail(),
                passwordEncoded,
                defaultRole);

        userRepository.save(newUser);

        profileService.createProfile(newUser.getUserId());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Success");
        return response;
    }
}
