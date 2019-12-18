package com.jean.sbc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	private Double discount;

	private Integer quantity;

	private Double price;

	public OrderItem() {

	}

	public OrderItem(Order order, Product product, Double discount, Integer quantity, Double price) {
		super();
		this.id.setProduct(product);
		this.id.setOrder(order);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}

	public double getSubTotal() {
		return (this.price - this.discount) * this.quantity;
	}

	public OrderItemPK getId() {
		return id;
	}

	public void setId(OrderItemPK id) {
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

	@JsonIgnore
	public Order getOrder() {
		return this.id.getOrder();
	}

	public Product getProduct() {
		return this.id.getProduct();
	}

	public void setOrder(Order order) {
		this.id.setOrder(order);
	}

	public void setProduct(Product product) {
		this.id.setProduct(product);
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		StringBuilder builder = new StringBuilder();
		builder.append(this.getProduct().getName());
		builder.append(", Quantity: ");
		builder.append(this.getQuantity());
		builder.append(", Unity price: ");
		builder.append(nf.format(this.getPrice()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(this.getSubTotal()));
		builder.append("\n");

		return builder.toString();
	}

}
