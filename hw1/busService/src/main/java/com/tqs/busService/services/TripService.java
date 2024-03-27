package com.tqs.busService.services;

import org.springframework.stereotype.Service;

import com.tqs.busService.model.Trip;
import com.tqs.busService.repositories.TripRepository;

import java.util.List;
import java.time.LocalDate;

@Service
public class TripService {
    final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public boolean saveTrip(Trip trip) {
        return tripRepository.saveTrip(trip);
    }

    public Trip getTripByCitiesAndNumberAndBus(String originCity, String destinationCity, int tripNumber, int busNumber) {
        return tripRepository.getTripByCitiesAndNumberAndBus(originCity, destinationCity, tripNumber, busNumber);
    }

    public List<Trip> getTripsByBothCities(String originCity, String destinationCity) {
        return tripRepository.findTripsByBothCities(originCity, destinationCity);
    }

    public List<Trip> getTripsByDate(LocalDate date) {
        return tripRepository.findTripsByDate(date);
    }
}
