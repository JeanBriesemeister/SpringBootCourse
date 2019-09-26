package com.jean.sbc.dto;

import java.io.Serializable;

import com.jean.sbc.domain.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

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
