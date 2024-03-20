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

    @GeneratedValue(strategy = GenerationType.UUID)
    private String token;

    public Reservation(Trip trip, Passenger passenger) {
        this.trip = trip;
        this.passenger = passenger;
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
}
