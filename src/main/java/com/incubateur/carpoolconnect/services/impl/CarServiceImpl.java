package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.repositories.CarRepository;
import com.incubateur.carpoolconnect.services.interfaces.CarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
}
