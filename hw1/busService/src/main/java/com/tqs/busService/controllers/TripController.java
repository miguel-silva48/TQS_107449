package com.tqs.busService.controllers;

import org.springframework.web.bind.annotation.*;

import com.tqs.busService.model.Trip;
import com.tqs.busService.services.TripService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class TripController {
    
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    //TODO talvez todas as pesquisas possam ter uma data default e quando é especificada uma data, a pesquisa é feita com essa data

    @RequestMapping("/trips")
    public ResponseEntity<Iterable<Trip>> getTripsByBothCities(@RequestParam(value = "origin") String originCityName, @RequestParam(value = "destination") String destinationCityName) {
        Iterable<Trip> trips = null;
        try {
            trips = tripService.getTripsByBothCities(originCityName, destinationCityName);
            return new ResponseEntity<>(trips, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(trips, HttpStatus.NOT_FOUND);
        }
    }


}
