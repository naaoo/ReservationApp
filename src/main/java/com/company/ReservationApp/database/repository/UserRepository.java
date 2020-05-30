package com.company.ReservationApp.database.repository;

import com.company.ReservationApp.database.model.User;

import java.util.ArrayList;

public class UserRepository {
    public ArrayList<User> userArr = new ArrayList<>();

    public UserRepository() {
        this.userArr.add(new User(1, "David Engleder", "david@mail.at", "testP"));
        this.userArr.add(new User(2, "Marina Feuerstein", "marina@mail.at", "1234"));
    }
}
