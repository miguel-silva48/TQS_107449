package com.tqs.busService.services;

import org.springframework.stereotype.Service;

import com.tqs.busService.model.City;
import com.tqs.busService.repositories.CityRepository;

import java.util.List;

@Service
public class CityService {
    final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }
    
    public City getCityByName(String name) {
        return cityRepository.findByName(name);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
