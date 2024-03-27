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

    public boolean saveCity(City city) {
        return cityRepository.saveCity(city);
    }
    
    public City getCityByName(String name) {
        return cityRepository.findCityByName(name);
    }

    public List<City> getAllCities() {
        return cityRepository.findAllCities();
    }
}
