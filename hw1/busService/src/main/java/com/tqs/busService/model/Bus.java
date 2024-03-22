package com.tqs.busService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "buses")
public class Bus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

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
}