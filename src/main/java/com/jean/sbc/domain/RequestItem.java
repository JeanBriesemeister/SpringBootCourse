package com.jean.sbc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class RequestItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RequestItemPK id = new RequestItemPK();

	private Double discount;

	private Integer quantity;

	private Double price;

	public RequestItem() {

	}

	public RequestItem(Request request, Product product, Double discount, Integer quantity, Double price) {
		super();
		this.id.setProduct(product);
		this.id.setRequest(request);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}

	public RequestItemPK getId() {
		return id;
	}

	public void setId(RequestItemPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Request getRequest() {
		return this.id.getRequest();
	}

	public Product getProduct() {
		return this.id.getProduct();
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
		RequestItem other = (RequestItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
