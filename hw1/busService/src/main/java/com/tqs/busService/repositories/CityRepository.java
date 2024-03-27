package com.tqs.busService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tqs.busService.model.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
    public boolean saveCity(City city);

    public City findCityByName(String name);

    public List<City> findAllCities();
}
