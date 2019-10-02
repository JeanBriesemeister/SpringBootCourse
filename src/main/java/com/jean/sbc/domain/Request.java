package com.jean.sbc.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date date;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "CUSTOMERID")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "ADDRESSID")
	private Address address;

	@OneToMany(mappedBy = "id.request")
	private Set<RequestItem> items = new HashSet<RequestItem>();

	public Request() {

	}

	public Request(Integer id, Date date, Customer customer, Address address) {
		super();
		this.id = id;
		this.date = date;
		this.customer = customer;
		this.address = address;
	}

	public double getTotalValue() {
		double sum = 0.0;
		for (RequestItem requestItem : items) {
			sum = sum + requestItem.getSubTotal();
		}

		return sum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<RequestItem> getItems() {
		return items;
	}

	public void setItems(Set<RequestItem> items) {
		this.items = items;
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
		Request other = (Request) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
