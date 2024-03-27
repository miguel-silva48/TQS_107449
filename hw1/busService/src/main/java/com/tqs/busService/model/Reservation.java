package com.tqs.busService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private Trip trip;

    private Passenger passenger;

    private int numSeats = 1;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String token;

    public Reservation(Trip trip, Passenger passenger, int numSeats) {
        this.trip = trip;
        this.passenger = passenger;
        if (numSeats > 1) {
            this.numSeats = numSeats;
        }
    }

    public Trip getTrip() {
        return trip;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getToken() {
        return token;
    }

    public int getNumSeats() {
        return numSeats;
    }
}
