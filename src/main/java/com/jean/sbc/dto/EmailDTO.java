package com.jean.sbc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Email(message = "Invalid e-mail")
	@NotEmpty(message = "Required field")
	private String email;

	public EmailDTO() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
