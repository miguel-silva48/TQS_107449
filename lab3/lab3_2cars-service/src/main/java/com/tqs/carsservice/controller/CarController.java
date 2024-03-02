package com.tqs.carsservice.controller;

import org.springframework.web.bind.annotation.*;

import com.tqs.carsservice.data.Car;
import com.tqs.carsservice.service.CarManagerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {
    
    private final CarManagerService carManagerService;

    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }

    @RequestMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @RequestMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) throws ResourceNotFoundException {
        Car car = carManagerService.getCarDetails(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + carId));
        return ResponseEntity.ok().body(car);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car saved = carManagerService.saveCar(car);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(saved, status);
    }
}
