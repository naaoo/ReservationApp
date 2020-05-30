package com.company.ReservationApp.database.model;

public class Restaurant {
    private int id;
    private String name;
    private Location location;
    private Cuisine cuisine;

    public Restaurant(int id, String name, Location location, Cuisine cuisine) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", cuisine=" + cuisine +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }
}