package com.company.ReservationApp.api;

import com.company.ReservationApp.controller.RestaurantController;
import com.company.ReservationApp.database.model.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class RestaurantApiController {
    RestaurantController restaurantController = new RestaurantController();

    private static RestaurantApiController instance = null;
    private RestaurantApiController() {}

    public static RestaurantApiController getInstance() {
        if (instance == null) {
            instance = new RestaurantApiController();
        }
        return instance;
    }

    @GetMapping("/restaurants")
    public ArrayList<Restaurant> all() {
        return restaurantController.getAll();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant singleRestaurant(@PathVariable int id) {
        return restaurantController.getSingle(id);
    }

    @PostMapping("/restaurants")
    public void restaurant(@RequestBody Restaurant restaurant) {
        restaurantController.restaurantRepository.restaurantArr.add(restaurant);
    }

}
