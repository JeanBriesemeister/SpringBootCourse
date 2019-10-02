package com.jean.sbc.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.jean.sbc.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numberOfParcelas;

	public PaymentWithCard() {

	}

	public PaymentWithCard(Integer id, PaymentStatus status, Request request, Integer numberOfParcelas) {
		super(id, status, request);
		this.setNumberOfParcelas(numberOfParcelas);
	}

	public Integer getNumberOfParcelas() {
		return numberOfParcelas;
	}

	public void setNumberOfParcelas(Integer numberOfParcelas) {
		this.numberOfParcelas = numberOfParcelas;
	}

}
