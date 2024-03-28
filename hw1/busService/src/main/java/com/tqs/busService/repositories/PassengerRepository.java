package com.tqs.busService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tqs.busService.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{
}