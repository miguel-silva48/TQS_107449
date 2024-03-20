package com.tqs.busService.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trips")
public class Trip {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    private City origin;

    private City destination;

    private Date date;

    private Bus bus;

    private int price;

    public Trip(City origin, City destination, Date date, Bus bus, int price) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.bus = bus;
        this.price = price;
    }

    public City getOrigin() {
        return origin;
    }

    public City getDestination() {
        return destination;
    }

    public Date getDate() {
        return date;
    }

    public Bus getBus() {
        return bus;
    }

    public int getPrice() {
        return price;
    }
}
