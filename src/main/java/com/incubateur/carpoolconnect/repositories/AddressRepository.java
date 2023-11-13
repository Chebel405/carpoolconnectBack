package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
