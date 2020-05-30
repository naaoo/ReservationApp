package com.company.ReservationApp.database.model;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    public ArrayList<Token> tokenArr = new ArrayList<>();

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList getTokenArr() {
        return tokenArr;
    }

}
