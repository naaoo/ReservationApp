package com.company.ReservationApp.controller;

import com.company.ReservationApp.database.model.Reservation;
import com.company.ReservationApp.database.repository.ReservationRepository;
import org.joda.time.DateTime;

import java.util.ArrayList;

public class ReservationController {
    public ReservationRepository reservationRepository = new ReservationRepository();

    private static ReservationController instance = null;
    public ReservationController() {}

    public static ReservationController getInstance() {
        if (instance == null) {
            instance = new ReservationController();
        }
        return instance;
    }

    public ArrayList<Reservation> getAll() {
        return reservationRepository.reservationArr;
    }

    // subtracts seats of all rivaling reservations from maximum capacity
    public int getMaxSeats(DateTime askedTime) {
        int maxSeats = reservationRepository.maxCapacity;
        for (Reservation reservation : reservationRepository.reservationArr) {
            if ((askedTime.isBefore(reservation.dateTime) && !askedTime.plusHours(2).isBefore(reservation.dateTime)
                        && !askedTime.plusHours(2).equals(reservation.dateTime))
                    || (askedTime.isAfter(reservation.dateTime) && !askedTime.isAfter(reservation.dateTime.plusHours(2))
                        && !askedTime.equals(reservation.dateTime.plusHours(2)))
                    || askedTime.equals(reservation.dateTime)) {
                maxSeats = maxSeats - reservation.getSeats();
            }
        }
        return maxSeats;
    }

    public void makeReservation(String timeString, int seats, String email) {
        reservationRepository.reservationArr.add(new Reservation(seats, email, timeString));
    }

    public ArrayList<Reservation> getUsersReservations(String email) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationRepository.reservationArr) {
            if (reservation.getEmail().equals(email)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    public void deleteReservation(int id) {
        for (int i = 0; i < reservationRepository.reservationArr.size(); i++) {
            if (reservationRepository.reservationArr.get(i).id == id) {
                reservationRepository.reservationArr.remove(i);
            }
        }
    }
}
