package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.repositories.RouteRepository;
import com.incubateur.carpoolconnect.services.interfaces.RouteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
}
