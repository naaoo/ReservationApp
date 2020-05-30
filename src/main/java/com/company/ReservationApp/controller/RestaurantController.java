package com.company.ReservationApp.controller;

import com.company.ReservationApp.database.model.Restaurant;
import com.company.ReservationApp.database.repository.RestaurantRepository;

import java.util.ArrayList;

public class RestaurantController {
    public RestaurantRepository restaurantRepository = new RestaurantRepository();

    private static RestaurantController instance = null;
    public RestaurantController() {}

    public static RestaurantController getInstance() {
        if (instance == null) {
            instance = new RestaurantController();
        }
        return instance;
    }

    public ArrayList<Restaurant> getAll() {
        return restaurantRepository.restaurantArr;
    }

    public Restaurant getSingle(int id) {
        Restaurant singleRestaurant = null;
        for (Restaurant restaurant : restaurantRepository.restaurantArr) {
            if (restaurant.getId() == id) {
                singleRestaurant = restaurant;
            }
        }
        return singleRestaurant;
    }
}
