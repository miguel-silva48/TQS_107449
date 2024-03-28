package com.tqs.busService.controllers;

import org.springframework.web.bind.annotation.*;

import com.tqs.busService.model.Reservation;
import com.tqs.busService.model.Passenger;
import com.tqs.busService.model.Trip;
import com.tqs.busService.model.City;

import com.tqs.busService.services.ReservationService;
import com.tqs.busService.services.TripService;

import jakarta.transaction.Transactional;

import com.tqs.busService.services.CityService;
import com.tqs.busService.services.PassengerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class ReservationController {

    private final ReservationService reservationService;
    private final TripService tripService;
    private final CityService cityService;
    private final PassengerService passengerService;

    @Autowired
    public ReservationController(ReservationService reservationService, TripService tripService, CityService cityService, PassengerService passengerService) {
        this.reservationService = reservationService;
        this.tripService = tripService;
        this.cityService = cityService;
        this.passengerService = passengerService;
    }

    /*
     * Creates a reservation for a trip
     *  
     * Uses the trip number together with the bus number, origin and destination cities to find the trip
     * This is to avoid having to pass the trip id in the request
    */
    @PostMapping("/reservation")
    public ResponseEntity<String> createReservation(@RequestParam(value = "origin") String origin,
            @RequestParam(value = "destination") String destination,
            @RequestParam(value = "tripNumber") int tripNumber,
            @RequestParam(value = "numSeats") int numSeats,
            @RequestParam(value = "passengerName") String passengerName,
            @RequestParam(value = "passengerEmail") String passengerEmail) 
    {
        City originCity = null;
        City destinationCity = null;
        Trip tripFound = null;
        Reservation reservationSaved = null;
        Passenger passenger = null;
        
        try {
            passenger = passengerService.savePassenger(new Passenger(passengerName, passengerEmail));
            if (passenger == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            originCity = cityService.getCityByName(origin);
            destinationCity = cityService.getCityByName(destination);
            if (originCity == null || destinationCity == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            tripFound = tripService.getTripByCitiesAndNumber(originCity, destinationCity, tripNumber);
            if (tripFound == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            reservationSaved = reservationService.createReservation(tripFound, passenger, numSeats);
            if (reservationSaved == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(reservationSaved.getToken(), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Gets a reservation by its token
     * 
     * @param token is a unique auto-generated String and given to the user after creating a reservation
     * This is to avoid login and having to pass the trip id in the request
    */
    @GetMapping("/reservation")
    public ResponseEntity<Reservation> getReservationByToken(@RequestParam(value = "token") String token) {
        Reservation reservation = null;
        try {
            reservation = reservationService.findReservationByToken(token);
            if (reservation == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(reservation, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Cancels a reservation by its token
     * 
     * @param token is an auto-generated String for each reservation and given to the user after creating a reservation
     * This is to avoid login and having to pass the trip id in the request
    */
    @Transactional
    @DeleteMapping("/reservation")
    public ResponseEntity<Boolean> cancelReservationByToken(@RequestParam(value = "token") String token) {
        Reservation reservationFound = null;
        try {
            reservationFound = reservationService.findReservationByToken(token);
            if (reservationFound != null) {
                reservationService.cancelReservationByToken(token);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
