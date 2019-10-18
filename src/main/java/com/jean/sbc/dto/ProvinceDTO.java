package com.jean.sbc.dto;

import java.io.Serializable;

import com.jean.sbc.domain.Province;

public class ProvinceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	public ProvinceDTO() {

	}

	public ProvinceDTO(Province province) {
		this.id = province.getId();
		this.name = province.getName();
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
