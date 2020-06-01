package com.company.ReservationApp.database.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.atomic.AtomicInteger;

public class Reservation {
    // atomic int is used only for "database"-purpose
    // in reality auto-increment in database would be used to generate id
    static AtomicInteger atomicInteger = new AtomicInteger();
    public int id;
    int seats;
    String email;
    public DateTime dateTime;
    // assumption: every reservation has a duration of 2h

    public Reservation(int amountPersons, String email, String timeString) {
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm");
        this.id = atomicInteger.incrementAndGet();
        this.seats = amountPersons;
        this.email = email;
        this.dateTime = df.parseDateTime(timeString);
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getEmail() {
        return email;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", seats=" + seats +
                ", email='" + email + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
