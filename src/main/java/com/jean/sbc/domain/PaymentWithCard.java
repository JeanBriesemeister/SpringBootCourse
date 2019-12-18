package com.jean.sbc.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.jean.sbc.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numberOfInstallments;

	public PaymentWithCard() {

	}

	public PaymentWithCard(Integer id, PaymentStatus status, Order order, Integer numberOfInstallments) {
		super(id, status, order);
		this.setNumberOfInstallments(numberOfInstallments);
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}

}
