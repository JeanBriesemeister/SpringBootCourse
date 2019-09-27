package com.jean.sbc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jean.sbc.domain.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Required field")
	@Length(min = 5, max = 80, message = "Lenght must be between 5 and 80 characters")
	private String name;

	public CategoryDTO() {

	}

	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
