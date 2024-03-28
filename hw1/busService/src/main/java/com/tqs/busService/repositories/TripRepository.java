package com.tqs.busService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tqs.busService.model.Trip;
import com.tqs.busService.model.City;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    //Vou obrigar a ter ambos os campos posso ativar para ver alternativas
    //public List<Trip> findTripByOriginName(String originName);
    //public List<Trip> findTripByDestinationName(String destinationName);

    public Trip findByOriginAndDestinationAndNumber(City originCity, City destinationCity, int tripNumber);

    public List<Trip> findByOriginAndDestination(City originCity, City destinationCity);

    public List<Trip> findByDate(LocalDate date);
}
