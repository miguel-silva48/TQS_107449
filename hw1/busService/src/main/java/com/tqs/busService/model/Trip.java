package com.tqs.busService.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trips")
public class Trip {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    private static int baseNumber = 1000;

    private int number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "origin_id", nullable = false)
    private City origin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_id", nullable = false)
    private City destination;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    private int price;

    public Trip() {
    }

    public Trip(City origin, City destination, LocalDate date, Bus bus, int price) {
        this.number = baseNumber++;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.bus = bus;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public City getOrigin() {
        return origin;
    }

    public City getDestination() {
        return destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public Bus getBus() {
        return bus;
    }

    public int getPrice() {
        return price;
    }
}
