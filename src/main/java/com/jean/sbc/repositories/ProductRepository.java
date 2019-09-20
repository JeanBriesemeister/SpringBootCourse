package com.jean.sbc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jean.sbc.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
