package com.jean.sbc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jean.sbc.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

}
