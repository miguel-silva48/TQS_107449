package com.tqs.carsservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tqs.carsservice.controller.CarController;
import com.tqs.carsservice.data.Car;
import com.tqs.carsservice.service.CarManagerService;

import java.beans.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarController_WithMockServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService carService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostValidCar_thenCreateCar() throws Exception {
        Car car = new Car("Mazda", "Miata");

        when(carService.saveCar(any(Car.class))).thenReturn(car);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Mazda")))
                .andExpect(jsonPath("$.model", is("Miata")));

        verify(carService, times(1)).saveCar(any(Car.class));
    }

    @Test
    void whenPostInvalidCar_thenStatus400() throws Exception {
        Car car = new Car("Mazda", "Miata");
        car.setMaker(null);

        when(carService.saveCar(any(Car.class))).thenReturn(car);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
                .andExpect(status().isBadRequest());

        car.setMaker("Mazda");
        car.setModel(null);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
                .andExpect(status().isBadRequest());

        verify(carService, times(0)).saveCar(any(Car.class));
    }

    @Test
    void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car car1 = new Car("Porsche", "Taycan");
        Car car2 = new Car("Mazda", "RX-7");
        List<Car> allCars = Arrays.asList(car1,car2);

        when(carService.getAllCars()).thenReturn(allCars);

        mvc.perform(
                get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].maker", is("Porsche")))
                .andExpect(jsonPath("$[0].model", is("Taycan")))
                .andExpect(jsonPath("$[1].maker", is("Mazda")))
                .andExpect(jsonPath("$[1].model", is("RX-7")));

        verify(carService, times(1)).getAllCars();
    }

    @Test 
    void givenCar_whenGetCarById_thenReturnCar() throws Exception {
        Car car = new Car("Porsche", "Taycan");

        when(carService.getCarDetails(1L)).thenReturn(Optional.of(car));

        mvc.perform(
                get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maker", is("Porsche")))
                .andExpect(jsonPath("$.model", is("Taycan")));

        verify(carService, times(1)).getCarDetails(anyLong());
    }

    @Test
    void givenNoCar_whenGetCarById_thenReturnNotFound() throws Exception {
        when(carService.getCarDetails(1L)).thenReturn(Optional.empty());

        mvc.perform(
                get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(carService, times(1)).getCarDetails(anyLong());
    }
}
