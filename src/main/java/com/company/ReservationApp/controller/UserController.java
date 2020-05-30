package com.company.ReservationApp.controller;

import com.company.ReservationApp.database.model.Token;
import com.company.ReservationApp.database.model.User;
import com.company.ReservationApp.database.repository.TokenRepository;
import com.company.ReservationApp.database.repository.UserRepository;
import java.util.ArrayList;

public class UserController {
    User user = null;

    public UserRepository userRepository = new UserRepository();
    public TokenRepository tokenRepository = new TokenRepository();

    private static UserController instance = null;
    public UserController() {}

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public ArrayList<User> getAll() {
        return userRepository.userArr;
    }

    public User getSingle(int id) {
        User singleUser = null;
        for (User user : userRepository.userArr) {
            if (user.getId() == id) {
                singleUser = user;
            }
        }
        return singleUser;
    }

    public Token userLogin(String email, String password) {
        Token token = null;
        for (User user : userRepository.userArr) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                this.user = user;
                //checks if token was already generated before, generates until not already taken
                boolean existing = false;
                while (!existing) {
                    token = new Token();
                    existing = true;
                    for (Token tok : tokenRepository.allTokens) {
                        if (tok.equals(token)) {
                            existing = false;
                        }
                    }
                }
                user.tokenArr.add(token);
                tokenRepository.allTokens.add(token);
            }
        }
        return token;
    }
}

