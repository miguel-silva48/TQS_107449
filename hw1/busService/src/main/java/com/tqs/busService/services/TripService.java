package com.tqs.busService.services;

import org.springframework.stereotype.Service;

import com.tqs.busService.model.Trip;
import com.tqs.busService.repositories.TripRepository;

import java.util.List;
import java.util.Date;

@Service
public class TripService {
    final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<Trip> getTripsByBothCities(String originCity, String destinationCity) {
        return tripRepository.findTripsByBothCities(originCity, destinationCity);
    }

    public List<Trip> getTripsByDate(Date date) {
        return tripRepository.findTripsByDate(date);
    }
}
