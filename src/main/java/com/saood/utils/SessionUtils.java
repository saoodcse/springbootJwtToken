package com.saood.utils;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class SessionUtils {

    public String generateSessionId() {
        StringBuilder sessionId = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int randomIndex = random.nextInt(characters.length());
            sessionId.append(characters.charAt(randomIndex));
        }

        return sessionId.toString();
    }
}
