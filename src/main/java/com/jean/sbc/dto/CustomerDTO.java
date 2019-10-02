package com.jean.sbc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jean.sbc.domain.Customer;
import com.jean.sbc.services.validation.CustomerUpdate;

@CustomerUpdate
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Required field")
	@Length(min = 5, max = 120, message = "Lenght must be between 5 and 80 characters")
	private String name;

	@NotEmpty
	@Email(message = "Invalid e-mail")
	@NotEmpty(message = "Required field")
	private String email;

	public CustomerDTO() {

	}

	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
