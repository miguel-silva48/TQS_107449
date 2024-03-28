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

    public Reservation createReservation(Trip trip, Passenger passenger, int numSeats) {
        Reservation reservation = new Reservation(trip, passenger, numSeats);
        return reservationRepository.save(reservation);
    }

    public Reservation findReservationByToken(String token) {
        return reservationRepository.findByToken(token);
    }

    public void cancelReservationByToken(String token) {
        reservationRepository.deleteByToken(token);
    }
}
