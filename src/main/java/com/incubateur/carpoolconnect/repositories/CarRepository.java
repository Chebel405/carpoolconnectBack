package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
