package com.jean.sbc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.Category;
import com.jean.sbc.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category find(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);

		return category.orElse(null);
	}
}
