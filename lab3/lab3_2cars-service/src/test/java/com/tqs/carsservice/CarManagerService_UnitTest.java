package com.tqs.carsservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tqs.carsservice.data.Car;
import com.tqs.carsservice.data.CarRepository;
import com.tqs.carsservice.service.CarManagerService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CarManagerService_UnitTest {
    
    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {
        Car car1 = new Car("Mazda", "Miata");
        car1.setCarId(1L);
        Car car2 = new Car("Porsche", "Taycan");
        Car car3 = new Car("Ford", "Focus");

        List<Car> allCars = Arrays.asList(car1, car2, car3);

        when(carRepository.findByCarId(1L)).thenReturn(car1);
        when(carRepository.findByCarId(-99L)).thenReturn(null);
        when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test 
    void whenValidId_thenCarShouldBeFound() {
        Long id = 1L;
        Car found = carService.getCarDetails(id).get();
        assertThat(found.getMaker()).isEqualTo("Mazda");
        verify(carRepository, times(1)).findByCarId(1L);
    }

    @Test
    void whenInValidId_thenCarShouldNotBeFound() {
        Long id = -99L;
        assertThrows(NullPointerException.class, () -> carService.getCarDetails(id));
        verify(carRepository, VerificationModeFactory.times(1)).findByCarId(id);
    }

    @Test
    void given3Cars_whengetAll_thenReturn3Records() {
        List<Car> allCars = carService.getAllCars();
        assertThat(allCars).hasSize(3).extracting(Car::getModel).contains("Miata", "Taycan", "Focus");
        verify(carRepository, times(1)).findAll();
    }
}
