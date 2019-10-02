package com.jean.sbc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jean.sbc.services.validation.CustomerInsert;

@CustomerInsert
public class CustomerNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Required field")
	@Length(min = 5, max = 80, message = "Lenght must be between 5 and 80 characters")
	private String name;

	@NotEmpty(message = "Required field")
	@Email(message = "Invalid e-mail")
	private String email;

	@NotEmpty(message = "Required field")
	private String financialCode;

	private Integer customerType;

	@NotEmpty(message = "Required field")
	private String street;

	@NotEmpty(message = "Required field")
	private String number;

	private String complement;

	private String district;

	@NotEmpty(message = "Required field")
	private String postalCode;

	@NotEmpty(message = "Required field")
	private String telephone1;

	private String telephone2;

	private String telephone3;

	private Integer cityId;

	public CustomerNewDTO() {

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

	public String getFinancialCode() {
		return financialCode;
	}

	public void setFinancialCode(String financialCode) {
		this.financialCode = financialCode;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getTelephone3() {
		return telephone3;
	}

	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}
