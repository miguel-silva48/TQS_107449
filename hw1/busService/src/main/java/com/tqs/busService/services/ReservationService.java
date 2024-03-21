package com.tqs.busService.services;

import org.springframework.stereotype.Service;

import com.tqs.busService.model.Reservation;
import com.tqs.busService.model.Trip;
import com.tqs.busService.model.Passenger;
import com.tqs.busService.repositories.ReservationRepository;

@Service
public class ReservationService {
    final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public String createReservation(Trip trip, Passenger passenger) {
        return reservationRepository.createReservation(trip, passenger);
    }

    public Reservation cancelReservationByToken(String token) {
        return reservationRepository.cancelReservationByToken(token);
    }

    public Reservation findReservationByToken(String token) {
        return reservationRepository.findReservationByToken(token);
    }
}
