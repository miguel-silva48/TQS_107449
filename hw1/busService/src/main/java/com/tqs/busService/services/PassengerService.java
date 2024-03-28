package com.tqs.busService.services;

import org.springframework.stereotype.Service;

import com.tqs.busService.model.Passenger;
import com.tqs.busService.repositories.PassengerRepository;

@Service
public class PassengerService {
    final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }
}
