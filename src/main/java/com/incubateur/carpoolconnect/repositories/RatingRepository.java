package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
