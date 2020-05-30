package com.company.ReservationApp.database.model;

import java.util.Random;

public class Token {
    public String token;

    public Token() {
        String token = "";
        Random random = new Random();
        // generates a random token (consisting of 5 letters, 4 numbers)
        for (int i = 0; i < 5; i++) {
            int num = 97 + random.nextInt(25);
            char charNum = (char)num;
            token = token + charNum;
        }
        for (int i = 0; i < 4; i++) {
            token = token + random.nextInt(10);
        }
        this.token = token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                '}';
    }
}
