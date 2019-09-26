package com.jean.sbc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jean.sbc.domain.Category;
import com.jean.sbc.dto.CategoryDTO;
import com.jean.sbc.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id) {
		Category category = categoryService.find(id);

		return ResponseEntity.ok().body(category);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Category category) {
		category = categoryService.insert(category);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Category category, @PathVariable Integer id) {
		category.setId(id);
		category = categoryService.update(category);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.delete(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> categories = categoryService.findAll();

		List<CategoryDTO> categoriesDto = categories.stream().map(category -> new CategoryDTO(category))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(categoriesDto);
	}
}
