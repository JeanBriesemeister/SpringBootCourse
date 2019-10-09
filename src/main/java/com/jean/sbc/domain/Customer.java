package com.jean.sbc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jean.sbc.domain.enums.CustomerType;
import com.jean.sbc.domain.enums.Profile;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = -5275965071840794191L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(unique = true)
	private String email;

	private String financialCode;

	private Integer customerType;

	@JsonIgnore
	private String password;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<Address>();

	@ElementCollection
	@CollectionTable(name = "TELEPHONE")
	private Set<String> telephones = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PROFILEs")
	private Set<Integer> profiles = new HashSet<Integer>();

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Request> requests = new ArrayList<Request>();

	public Customer() {
		this.addProfile(Profile.CUSTOMER);
	}

	public Customer(Integer id, String name, String email, String financialCode, CustomerType customerType,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.financialCode = financialCode;
		this.customerType = customerType == null ? null : customerType.getCod();
		this.password = password;

		this.addProfile(Profile.CUSTOMER);
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

	public String getFinancialCode() {
		return financialCode;
	}

	public void setFinancialCode(String financialCode) {
		this.financialCode = financialCode;
	}

	public CustomerType getCustomerType() {
		return CustomerType.toEnum(customerType);
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType.getCod();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Profile> getProfiles() {
		return this.profiles.stream().map(profile -> Profile.toEnum(profile)).collect(Collectors.toSet());
	}

	public void addProfile(Profile profile) {
		this.profiles.add(profile.getCod());
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getTelephones() {
		return telephones;
	}

	public void setTelephones(Set<String> telephones) {
		this.telephones = telephones;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
