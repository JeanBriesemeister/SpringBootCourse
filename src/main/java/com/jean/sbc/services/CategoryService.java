package com.jean.sbc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.Category;
import com.jean.sbc.repositories.CategoryRepository;
import com.jean.sbc.services.exception.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category find(Integer id) {
		Optional<Category> obj = categoryRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Class: " + Category.class.getName()));
	}
}
