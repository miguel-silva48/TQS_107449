package com.tqs.busService.controllers;

import org.springframework.web.bind.annotation.*;

import com.tqs.busService.model.Reservation;
import com.tqs.busService.services.ReservationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class ReservationController {
    
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //TODO aqui vai dar asneira maybe
    // @PostMapping("/reservation")
    // public ResponseEntity<String> createReservation(@RequestParam(value = "trip") Long tripId, @RequestParam(value = "passenger") Long passengerId) {
    //     String token = null;
    //     try {
    //         token = reservationService.createReservation(tripId, passengerId);
    //         return new ResponseEntity<>(token, HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(token, HttpStatus.NOT_FOUND);
    //     }
    // }

    @RequestMapping("/reservation")
    public ResponseEntity<Reservation> getReservationByToken(@RequestParam(value = "token") String token) {
        Reservation reservation = null;
        try {
            reservation = reservationService.findReservationByToken(token);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(reservation, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reservation")
    public ResponseEntity<Reservation> cancelReservationByToken(@RequestParam(value = "token") String token) {
        Reservation reservation = null;
        try {
            reservation = reservationService.cancelReservationByToken(token);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(reservation, HttpStatus.NOT_FOUND);
        }
    }
}
