package com.jean.sbc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.Category;
import com.jean.sbc.repositories.CategoryRepository;
import com.jean.sbc.services.exception.DataIntegrityException;
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

	public Category insert(Category category) {
		category.setId(null);

		return categoryRepository.save(category);
	}

	public Category update(Category category) {
		this.find(category.getId());

		return categoryRepository.save(category);
	}

	public void delete(Integer id) {
		try {
			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Category has products!");
		}
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

}
