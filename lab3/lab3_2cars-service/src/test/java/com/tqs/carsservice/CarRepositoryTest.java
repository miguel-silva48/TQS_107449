package com.tqs.carsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.tqs.carsservice.data.Car;
import com.tqs.carsservice.data.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindCarById_thenReturnCar() {
        Car car = new Car("Porsche", "Taycan");
        entityManager.persistAndFlush(car);

        Car found = carRepository.findByCarId(car.getCarId());
        assertThat(found.getModel()).isEqualTo(car.getModel());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-111L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car car1 = new Car("Mazda", "Miata");
        Car car2 = new Car("Porsche", "Taycan");
        Car car3 = new Car("Ford", "Focus");

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(car1.getModel(), car2.getModel(), car3.getModel());
    }
}
