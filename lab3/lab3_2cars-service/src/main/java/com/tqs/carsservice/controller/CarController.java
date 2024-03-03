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

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car carToSave) {
        Car car = null;
        try {
            car = carManagerService.saveCar(carToSave);
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(car, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @RequestMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) throws ResourceNotFoundException {
        Car car = null;
        try {
            car = carManagerService.getCarDetails(carId).get();
            return new ResponseEntity<Car>(car, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Car>(car, HttpStatus.NOT_FOUND);
        }
    }
}
