package com.company.ReservationApp.database.repository;

import com.company.ReservationApp.database.model.Cuisine;
import com.company.ReservationApp.database.model.Location;
import com.company.ReservationApp.database.model.Restaurant;

import java.util.ArrayList;

public class RestaurantRepository {
    public ArrayList<Restaurant> restaurantArr = new ArrayList<>();

    public RestaurantRepository() {
        this.restaurantArr.add(new Restaurant(1, "Burger Master", Location.BREGENZ, Cuisine.AMERICAN));
        this.restaurantArr.add(new Restaurant(2, "Taj Mahal", Location.BLUDENZ, Cuisine.INDIAN));
        this.restaurantArr.add(new Restaurant(3, "Gasthaus", Location.FELDKIRCH, Cuisine.AUSTRIAN));
    }
}
