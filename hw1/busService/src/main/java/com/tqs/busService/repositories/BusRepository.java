package com.tqs.busService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tqs.busService.model.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long>{
}