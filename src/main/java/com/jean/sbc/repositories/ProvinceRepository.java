package com.jean.sbc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jean.sbc.domain.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

}
