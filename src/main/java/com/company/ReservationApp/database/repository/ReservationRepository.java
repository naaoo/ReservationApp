package com.company.ReservationApp.database.repository;

import com.company.ReservationApp.database.model.Reservation;

import java.util.ArrayList;

public class ReservationRepository {
    public int maxCapacity = 20;
    public ArrayList<Reservation> reservationArr = new ArrayList<>();

    public ReservationRepository() {
        this.reservationArr.add(new Reservation(6, "david@mail.at", "2020/05/27 18:00"));
        this.reservationArr.add(new Reservation(2, "marina@mail.at", "2020/05/27 19:00"));
        this.reservationArr.add(new Reservation(4, "david@mail.at", "2020/05/28 18:00"));
    }
}
