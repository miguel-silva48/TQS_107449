package com.tqs.busService.controllers;

import org.springframework.web.bind.annotation.*;

import com.tqs.busService.model.City;
import com.tqs.busService.services.CityService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    /*
     * Saves a city
     * 
     * @param city The city to be saved
     * This saves a city to the database (inaccessible to the end-user)
     * 
     * Checks if the city already exists in the database
     * 
     * @return true if the city was saved, false otherwise
     */
    @PostMapping("/city")
    public ResponseEntity<Boolean> saveCity(@RequestBody City city) {
        try {
            if (cityService.getCityByName(city.getName()) instanceof City) {
                return new ResponseEntity<>(false, HttpStatus.CONFLICT);
            } 
            boolean isSaved = cityService.saveCity(city);
            if (isSaved) {
                return new ResponseEntity<>(true, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Gets a city by its name
     * 
     * @param cityName The name of the city to be found
     * This is to avoid a search for an adultered city name
     */
    @GetMapping("/city")
    public ResponseEntity<City> getCityByName(@RequestParam(value = "name") String cityName) {
        City city = null;
        try {
            city = cityService.getCityByName(cityName);
            if (city == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(city, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Gets a list of all cities
     * 
     * This allows the user to select between a list of all available cities before making a reservation
     */
    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> citiesFound;
        try {
            citiesFound = cityService.getAllCities();
            if (citiesFound.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(citiesFound, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
