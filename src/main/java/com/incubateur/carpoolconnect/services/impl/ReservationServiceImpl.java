package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.repositories.ReservationRepository;
import com.incubateur.carpoolconnect.services.interfaces.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
}
