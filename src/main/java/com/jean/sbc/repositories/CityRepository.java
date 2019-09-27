package com.jean.sbc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jean.sbc.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}