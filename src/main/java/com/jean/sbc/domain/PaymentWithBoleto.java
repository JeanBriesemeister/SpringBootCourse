package com.jean.sbc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jean.sbc.domain.enums.PaymentStatus;

@Entity
public class PaymentWithBoleto extends Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dueDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date paymentDate;

	public PaymentWithBoleto() {

	}

	public PaymentWithBoleto(Integer id, PaymentStatus status, Request request, Date dueDate, Date paymentDate) {
		super(id, status, request);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
