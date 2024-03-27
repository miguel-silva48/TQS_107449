package com.tqs.busService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tqs.busService.model.Reservation;
import com.tqs.busService.model.Trip;
import com.tqs.busService.model.Passenger;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    public String createReservation(Trip trip, Passenger passenger);

    public Reservation findReservationByToken(String token);
    
    public boolean cancelReservationByToken(String token);
}
