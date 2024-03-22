package com.tqs.carsservice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import com.tqs.carsservice.data.Car;
import com.tqs.carsservice.data.CarRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.beans.Transient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CarControllerRealIT {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateCar() {
        Car car = new Car("Porsche", "Taycan");
        restTemplate.postForEntity("/api/cars", car, Car.class);
        assertThat(repository.findAll()).extracting(Car::getMaker).containsOnly("Porsche");
    }

    @Test
    void whenInvalidInput_thenBadRequest() {
        Car car = new Car("Porsche", "Taycan");
        car.setMaker(null);
        assertThrows(IllegalArgumentException.class, () -> {
            restTemplate.postForEntity("/api/cars", car, Car.class);
        });
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void givenCars_whenGetCarById_thenStatus200() {
        Car car1 = new Car("Porsche", "Taycan");
        Car car2 = new Car("Mazda", "Miata");
        repository.saveAndFlush(car1);
        repository.saveAndFlush(car2);

        Car found = restTemplate.getForObject("/api/cars/" + car1.getCarId(), Car.class);
        assertThat(found.getMaker()).isEqualTo(car1.getMaker());
    }

    @Test
    void givenCars_whenGetCars_thenStatus200() {
        Car car1 = new Car("Porsche", "Taycan");
        Car car2 = new Car("Mazda", "Miata");
        repository.saveAndFlush(car1);
        repository.saveAndFlush(car2);

        Car[] carsList = restTemplate.getForObject("/api/cars", Car[].class);
        assertThat(carsList).extracting(Car::getMaker).containsExactly("Porsche", "Mazda");
    }

    @Test
    void givenCars_whenGetCarByInvalidId_thenStatus404() {
        Car car1 = new Car("Porsche", "Taycan");
        repository.saveAndFlush(car1);

        Car found = restTemplate.getForObject("/api/cars/2", Car.class);
        assertThat(found).isNull();
    }
}
