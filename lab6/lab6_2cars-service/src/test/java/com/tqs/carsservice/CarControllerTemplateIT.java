package com.tqs.carsservice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.tqs.carsservice.data.Car;
import com.tqs.carsservice.data.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase

// switch AutoConfigureTestDatabase with TestPropertySource to use a real database
// @TestPropertySource( locations = "application-integrationtest.properties")
class CarControllerTemplateIT {
    
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
        ResponseEntity<Car> response = restTemplate.postForEntity("/api/cars", car, Car.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        
        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("Porsche");
    }

    @Test 
    void whenInvalidInput_thenStatus400() {
        Car car1 = new Car("Porsche", null);
        ResponseEntity<Car> response = restTemplate.postForEntity("/api/cars", car1, Car.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Car car2 = new Car(null, "Taycan");
        ResponseEntity<Car> response2 = restTemplate.postForEntity("/api/cars", car2, Car.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void givenCars_whenGetCarById_thenStatus200() {
        Car car1 = new Car("Porsche", "Taycan");
        Car car2 = new Car("Mazda", "Miata");
        repository.saveAndFlush(car1);
        repository.saveAndFlush(car2);

        ResponseEntity<Car> response = restTemplate.getForEntity("/api/cars/2", Car.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMaker()).isEqualTo("Mazda");
    }

    @Test
    void givenCars_whenGetCars_thenStatus200()  {
        Car car1 = new Car("Porsche", "Taycan");
        Car car2 = new Car("Mazda", "Miata");
        repository.saveAndFlush(car1);
        repository.saveAndFlush(car2);

        ResponseEntity<List<Car>> response = restTemplate.exchange(
                "/api/cars",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Car>>() {
                });
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("Porsche", "Mazda");
    }

    @Test
    void givenCars_whenGetCarByInvalidId_thenstatus404() {
        Car car1 = new Car("Porsche", "Taycan");
        repository.saveAndFlush(car1);

        ResponseEntity<Car> response = restTemplate.getForEntity("/api/cars/4", Car.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
