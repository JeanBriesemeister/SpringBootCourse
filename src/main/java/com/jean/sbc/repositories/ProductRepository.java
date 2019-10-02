package com.jean.sbc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jean.sbc.domain.Category;
import com.jean.sbc.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT product FROM Product product INNER JOIN product.categories categories WHERE product.name LIKE %:name% AND categories IN :categories")
	Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories,
			Pageable pageRequest);

	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories,
			Pageable pageRequest);

}
