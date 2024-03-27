package com.tqs.busService.controllers;

import org.springframework.web.bind.annotation.*;

import com.tqs.busService.model.City;
import com.tqs.busService.model.Trip;
import com.tqs.busService.services.TripService;
import com.tqs.busService.services.CityService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TripController {

    private final TripService tripService;
    private final CityService cityService;

    public TripController(TripService tripService, CityService cityService) {
        this.tripService = tripService;
        this.cityService = cityService;
    }

    // TODO talvez todas as pesquisas possam ter uma data default (atual?) e quando
    // é especificada uma data, a pesquisa é feita com essa data

    /*
     * Gets a list of trips
     * 
     * Checks if the origin and destination cities exist in the database to prevent
     * adultered cities
     * 
     * @return A list of trips that go from the origin city to the destination city
     */
    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getTripsByBothCities(@RequestParam(value = "origin") String origin,
            @RequestParam(value = "destination") String destination) {
        List<Trip> trips;
        try {
            if (cityService.getCityByName(origin) instanceof City
                    && cityService.getCityByName(destination) instanceof City) {
                trips = tripService.getTripsByBothCities(origin, destination);
                if (trips.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>(trips, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Gets a list of trips
     * 
     * Checks if the origin city exists in the database to prevent duplicates
     * 
     * @return A boolean indicating if the trip was saved
     */
    @PostMapping("/trip")
    public ResponseEntity<Boolean> saveTrip(@RequestBody Trip trip) {
        try {
            boolean isSaved = tripService.saveTrip(trip);
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
     * Gets a trip by its origin and destination cities, number and bus number
     * 
     * This is to avoid having to pass the trip id in the request and adultered cities or bus numbers
     */
    @GetMapping("/trip")
    public ResponseEntity<Trip> getTripByCitiesAndNumberAndBus(@RequestParam(value = "origin") String originCityName,
            @RequestParam(value = "destination") String destinationCityName,
            @RequestParam(value = "busNumber") int tripNumber, @RequestParam(value = "bus") int busNumber) 
    {
        Trip trip = null;
        try {
            trip = tripService.getTripByCitiesAndNumberAndBus(originCityName, destinationCityName, tripNumber, busNumber);
            if (trip == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(trip, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
