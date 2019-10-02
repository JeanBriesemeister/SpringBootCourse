package com.jean.sbc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.jean.sbc.domain.PaymentWithBoleto;

@Service
public class BoletoService {

	public void fillPaymentWithBoleto(PaymentWithBoleto payment, Date paymentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(paymentDate);
		calendar.add(Calendar.DAY_OF_MONTH, 7);

		payment.setDueDate(calendar.getTime());
	}

}
