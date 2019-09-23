package com.jean.sbc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jean.sbc.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
