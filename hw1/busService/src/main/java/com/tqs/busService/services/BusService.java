package com.tqs.busService.services;

import org.springframework.stereotype.Service;

import com.tqs.busService.model.Bus;
import com.tqs.busService.repositories.BusRepository;

@Service
public class BusService {
    final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }
}
