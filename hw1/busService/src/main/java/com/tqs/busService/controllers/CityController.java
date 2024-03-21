package com.tqs.busService.controllers;

import org.springframework.web.bind.annotation.*;

import com.tqs.busService.model.City;
import com.tqs.busService.services.CityService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class CityController {
    
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping("/city")
    public ResponseEntity<City> getCityByName(@RequestParam(value = "name") String cityName) {
        City city = null;
        try {
            city = cityService.getCityByName(cityName);
            return new ResponseEntity<>(city, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(city, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/cities")
    public ResponseEntity<Iterable<City>> getAllCities() {
        Iterable<City> cities = null;
        try {
            cities = cityService.getAllCities();
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(cities, HttpStatus.NOT_FOUND);
        }
    }
}
