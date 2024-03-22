package com.tqs.carsservice.controller;

import org.springframework.web.bind.annotation.*;

import com.tqs.carsservice.data.Car;
import com.tqs.carsservice.service.CarManagerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {
    
    private final CarManagerService carManagerService;

    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car carToSave) {
        Car car = null;
        if (carToSave.getMaker() == null || carToSave.getModel() == null) {
            return new ResponseEntity<>(car, HttpStatus.BAD_REQUEST);
        }
        car = carManagerService.saveCar(carToSave);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) {
        Car car = null;
        try {
            Optional<Car> optionalCar = carManagerService.getCarDetails(carId);
            if (optionalCar.isPresent()) {
                car = optionalCar.get();
                return new ResponseEntity<>(car, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(car, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(car, HttpStatus.NOT_FOUND);
        }
    }
}
