package com.f5.buzon_inteligente_BE.auth.logout;

import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    private final Set<String> blacklistedTokens = new HashSet<>();

    public void blacklistedTokens(String token){
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token){
        return blacklistedTokens.contains(token);
    }
}
