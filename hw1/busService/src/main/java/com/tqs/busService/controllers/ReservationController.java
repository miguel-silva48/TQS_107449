package com.tqs.busService.controllers;

import org.springframework.web.bind.annotation.*;

import com.tqs.busService.model.Reservation;
import com.tqs.busService.model.Passenger;
import com.tqs.busService.model.Trip;

import com.tqs.busService.services.ReservationService;
import com.tqs.busService.services.TripService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class ReservationController {

    private final ReservationService reservationService;
    private final TripService tripService;

    public ReservationController(ReservationService reservationService, TripService tripService) {
        this.reservationService = reservationService;
        this.tripService = tripService;
    }

    /*
     * Creates a reservation for a trip
     *  
     * Uses the trip number together with the bus number, origin and destination cities to find the trip
     * This is to avoid having to pass the trip id in the request
    */
    @PostMapping("/reservation")
    public ResponseEntity<String> createReservation(@RequestParam(value = "originCity") String originCity,
            @RequestParam(value = "destinationCity") String destinationCity,
            @RequestParam(value = "tripNumber") int tripNumber,
            @RequestParam(value = "busNumber") int busNumber,
            @RequestParam(value = "passengerName") String passengerName,
            @RequestParam(value = "passengerEmail") String passengerEmail) 
    {
        String reservationToken = null;
        Passenger passenger = new Passenger(passengerName, passengerEmail);
        try {
            Trip tripFound = tripService.getTripByCitiesAndNumberAndBus(originCity, destinationCity, tripNumber, busNumber);
            if (tripFound == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            reservationToken = reservationService.createReservation(tripFound, passenger);
            if (reservationToken == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(reservationToken, HttpStatus.CREATED);
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
    @DeleteMapping("/reservation")
    public ResponseEntity<Boolean> cancelReservationByToken(@RequestParam(value = "token") String token) {
        try {
            boolean isCanceled = reservationService.cancelReservationByToken(token);
            if (isCanceled) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
