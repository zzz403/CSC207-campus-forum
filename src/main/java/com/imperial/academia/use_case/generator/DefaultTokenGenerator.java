package com.imperial.academia.use_case.generator;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class DefaultTokenGenerator implements TokenGenerator{
    

    @Override
    public String generateToken(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[24];
        secureRandom.nextBytes(token);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
    }

    public long getTokenExpiryTime(int duration, TimeUnit unit){
        return System.currentTimeMillis() + unit.toMillis(duration);
    }
}
