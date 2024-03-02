package com.tqs.carsservice.service;

import org.springframework.stereotype.Service;

import com.tqs.carsservice.data.Car;
import com.tqs.carsservice.data.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {
    final CarRepository carRepository;
    
    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId) {
        return Optional.of(carRepository.findByCarId(carId));
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }
}
