package com.tqs.busService.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "reservations")
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    private int numSeats = 1;

    private String token;

    public Reservation() {
    }

    public Reservation(Trip trip, Passenger passenger, int numSeats) {
        this.trip = trip;
        this.passenger = passenger;
        if (numSeats > 1) {
            this.numSeats = numSeats;
        }
        this.token = generateUniqueToken();
    }

    private String generateUniqueToken() {
        return UUID.randomUUID().toString();
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
