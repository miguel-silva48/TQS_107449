package com.tqs.busService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "buses")
public class Bus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int number;

    private String plate;

    private int capacity;

    public Bus(String plate, int capacity) {
        this.plate = plate;
        this.capacity = capacity;
    }

    public String getPlate() {
        return plate;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumber() {
        return number;
    }
}
