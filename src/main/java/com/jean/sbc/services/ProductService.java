package com.jean.sbc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.Category;
import com.jean.sbc.domain.Product;
import com.jean.sbc.repositories.CategoryRepository;
import com.jean.sbc.repositories.ProductRepository;
import com.jean.sbc.services.exception.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Product find(Integer id) {
		Optional<Product> obj = productRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Class: " + Product.class.getName()));
	}

	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String orderByDirection) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(orderByDirection), orderBy);

		List<Category> categories = categoryRepository.findAllById(ids);

		return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
}
