package com.tqs.busService.services;

import org.springframework.stereotype.Service;

import com.tqs.busService.model.Trip;
import com.tqs.busService.model.City;
import com.tqs.busService.repositories.TripRepository;

import java.util.List;
import java.time.LocalDate;

@Service
public class TripService {
    final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip getTripByCitiesAndNumber(City originCity, City destinationCity, int tripNumber) {
        return tripRepository.findByOriginAndDestinationAndNumber(originCity, destinationCity, tripNumber);
    }

    public List<Trip> getTripsByBothCities(City originCity, City destinationCity) {
        return tripRepository.findByOriginAndDestination(originCity, destinationCity);
    }

    public List<Trip> getTripsByDate(LocalDate date) {
        return tripRepository.findByDate(date);
    }
}
