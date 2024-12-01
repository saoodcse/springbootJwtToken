package com.saood.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RegistrationUtils {
    public String generateRegistrationId() {
        StringBuilder registrationId = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        for (int i = 0; i < 18; i++) {
            int randomIndex = random.nextInt(characters.length());
            registrationId.append(characters.charAt(randomIndex));
        }

        return registrationId.toString();
    }

}
