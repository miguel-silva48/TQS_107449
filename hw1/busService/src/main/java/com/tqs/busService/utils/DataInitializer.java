package com.tqs.busService.utils;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import com.tqs.busService.model.*;
import com.tqs.busService.services.*;

import java.util.Arrays;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final CityService cityService;
    private final TripService tripService;
    private final BusService busService;

    public DataInitializer(CityService cityService, TripService tripService, BusService busService) {
        this.cityService = cityService;
        this.tripService = tripService;
        this.busService = busService;
    }

    @Override
    public void run(String... args) throws Exception {

        //Create and save cities
        City porto = new City("Porto");
        City lisboa = new City("Lisboa");
        City braga = new City("Braga");
        City aveiro = new City("Aveiro");
        City coimbra = new City("Coimbra");
        City faro = new City("Faro");
        City viseu = new City("Viseu");

        Arrays.asList(porto, lisboa, braga, aveiro, coimbra, faro, viseu)
            .forEach(city -> cityService.saveCity(city));

        //Create and save buses
        Bus bus1 = new Bus("BUS-01", 27);
        Bus bus2 = new Bus("BUS-02", 36);
        Bus bus3 = new Bus("BUS-03", 40);
        Bus bus4 = new Bus("BUS-04", 55);
        Bus bus5 = new Bus("BUS-05", 70);
        Bus bus6 = new Bus("BUS-06", 12);
        Bus bus7 = new Bus("BUS-07", 30);
        Bus bus8 = new Bus("BUS-08", 44);
        Bus bus9 = new Bus("BUS-09", 23);
        Bus bus10 = new Bus("BUS-10", 62);

        Arrays.asList(bus1, bus2, bus3, bus4, bus5, bus6, bus7, bus8, bus9, bus10)
            .forEach(bus -> busService.saveBus(bus));

        //Create dates
        LocalDate date1 = LocalDate.of(2024, 5, 10);
        LocalDate date2 = LocalDate.of(2024, 5, 11);
        LocalDate date3 = LocalDate.of(2024, 5, 12);
        LocalDate date4 = LocalDate.of(2024, 5, 13);
        LocalDate date5 = LocalDate.of(2024, 5, 14);
        LocalDate date6 = LocalDate.of(2024, 5, 15);
        LocalDate date7 = LocalDate.of(2024, 5, 16);
        LocalDate date8 = LocalDate.of(2024, 5, 17);

        //Create and save trips
        Trip trip1 = new Trip(porto, lisboa, date1, bus1, 20);
        Trip trip2 = new Trip(porto, braga, date2, bus2, 10);
        Trip trip3 = new Trip(porto, aveiro, date3, bus3, 15);
        Trip trip4 = new Trip(porto, coimbra, date4, bus4, 25);
        Trip trip5 = new Trip(porto, faro, date5, bus5, 30);
        Trip trip6 = new Trip(porto, viseu, date6, bus6, 12);
        Trip trip7 = new Trip(lisboa, braga, date7, bus7, 18);
        Trip trip8 = new Trip(lisboa, aveiro, date8, bus8, 22);
        Trip trip9 = new Trip(lisboa, coimbra, date1, bus9, 27);
        Trip trip10 = new Trip(lisboa, faro, date2, bus10, 35);
        Trip trip11 = new Trip(lisboa, viseu, date3, bus1, 16);
        Trip trip12 = new Trip(aveiro, porto, date4, bus2, 20);
        Trip trip13 = new Trip(aveiro, lisboa, date5, bus3, 10);
        Trip trip14 = new Trip(aveiro, braga, date6, bus4, 15);
        Trip trip15 = new Trip(aveiro, coimbra, date7, bus5, 25);
        Trip trip16 = new Trip(aveiro, faro, date8, bus6, 30);
        Trip trip17 = new Trip(aveiro, viseu, date1, bus7, 12);

        Arrays.asList(trip1, trip2, trip3, trip4, trip5, trip6, trip7, trip8, trip9, trip10, trip11, trip12, trip13, trip14, trip15, trip16, trip17)
            .forEach(trip -> tripService.saveTrip(trip));


        //TODO maybe remove later
        //Create passengers    
        Passenger p1 = new Passenger("João Silva", "jsilva@ua.pt");
        Passenger p2 = new Passenger("Maria Santos", "msantos@ua.pt");
        
        //Create reservations
        Reservation r1 = new Reservation(trip1, p1, 2);
        Reservation r2 = new Reservation(trip2, p2, 1);
    }
}
