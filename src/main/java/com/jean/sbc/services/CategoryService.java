package com.jean.sbc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.Category;
import com.jean.sbc.dto.CategoryDTO;
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
		Category categoryDB = this.find(category.getId());
		this.updateData(categoryDB, category);

		return categoryRepository.save(category);
	}
	
	private void updateData(Category categoryDB, Category category) {
		categoryDB.setName(category.getName());
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

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String orderByDirection) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(orderByDirection), orderBy);

		return categoryRepository.findAll(pageRequest);
	}

	public Category fromDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getName());
	}

}
