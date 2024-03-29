package com.tqs.busService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tqs.busService.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
    public City findByName(String name);
}
