package com.f5.buzon_inteligente_BE.auth.logout;

import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    private final Set<String> blacklistToken = new HashSet<>();

    public void blacklistToken(String token){
        blacklistToken.add(token);
    }

    public boolean isTokenBlacklisted(String token){
        return blacklistToken.contains(token);
    }
}
