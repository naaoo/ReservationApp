package com.company.ReservationApp.api;

import com.company.ReservationApp.controller.ReservationController;
import com.company.ReservationApp.database.model.Reservation;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class ReservationApiController {
    ReservationController reservationController = new ReservationController();

    private static ReservationApiController instance = null;
    public ReservationApiController() {}

    public static ReservationApiController getInstance() {
        if (instance == null) {
            instance = new ReservationApiController();
        }
        return instance;
    }

    @GetMapping("/maxSeats")
    public int getMaxSeats(String timeString) {
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm");
        DateTime time = df.parseDateTime(timeString);
        int maxSeats = reservationController.getMaxSeats(time);
        return maxSeats;
    }

    @PostMapping("/reservation")
    public int postReservation(String timeString, String seatsString, String email) {
        int seats = Integer.parseInt(seatsString);
        reservationController.makeReservation(timeString, seats, email);
        //Todo: Success (or id) response..
        //return id doesn't work yet (not able to read return in js)
        int id = reservationController.reservationRepository.reservationArr
                .get(reservationController.reservationRepository.reservationArr.size() - 1).id;
        return id;
    }

    @GetMapping("/reservation/{email}")
    public ArrayList<Reservation> getReservations(@PathVariable String email) {
        return reservationController.getUsersReservations(email);
    }

    @DeleteMapping("/reservation/{id}")
    public void deleteReservation(@PathVariable int id) {
        reservationController.deleteReservation(id);
    }

    @PutMapping("/reservation")
    public void putReservation(int id, int seats) {
        boolean updated = reservationController.updateReservation(id, seats);
    }
}