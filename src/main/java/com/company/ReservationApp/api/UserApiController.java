package com.company.ReservationApp.api;

import com.company.ReservationApp.controller.UserController;
import com.company.ReservationApp.database.model.Token;
import com.company.ReservationApp.database.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// Contains User and Token functions

@CrossOrigin
@RestController
public class UserApiController {
    public UserController userController = new UserController();

    private static UserApiController instance = null;
    public UserApiController() {}

    public static UserApiController getInstance() {
        if (instance == null) {
            instance = new UserApiController();
        }
        return instance;
    }

    @GetMapping("/users")
    public ArrayList<User> all() {
        return userController.getAll();
    }

    @GetMapping("/users/{id}")
    public User singleUser(@PathVariable int id) {
        return userController.getSingle(id);
    }

    @PostMapping("/users")
    public void user(@RequestBody User user) {
        userController.userRepository.userArr.add(user);
    }

    @PostMapping("/api/session")
    public void login(String email, String password) {
        Token token = userController.userLogin(email, password);

    }
}
