package com.jean.sbc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jean.sbc.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
