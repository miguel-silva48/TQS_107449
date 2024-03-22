package com.tqs.busService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tqs.busService.model.Trip;

import java.util.List;
import java.util.Date;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    //Vou obrigar a ter ambos os campos posso ativar para ver alternativas
    //public List<Trip> findTripByOriginName(String originName);
    //public List<Trip> findTripByDestinationName(String destinationName);

    public List<Trip> findTripsByBothCities(String originCity, String destinationCity);

    public List<Trip> findTripsByDate(Date date);
}