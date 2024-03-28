package com.tqs.busService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tqs.busService.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    public Reservation findByToken(String token);
    
    public void deleteByToken(String token);
}
