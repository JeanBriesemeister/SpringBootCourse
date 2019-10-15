package com.jean.sbc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jean.sbc.domain.Customer;
import com.jean.sbc.domain.PaymentWithBoleto;
import com.jean.sbc.domain.Request;
import com.jean.sbc.domain.RequestItem;
import com.jean.sbc.domain.enums.PaymentStatus;
import com.jean.sbc.repositories.PaymentRepository;
import com.jean.sbc.repositories.RequestItemRepository;
import com.jean.sbc.repositories.RequestRepository;
import com.jean.sbc.security.UserSS;
import com.jean.sbc.services.exception.AuthorizationException;
import com.jean.sbc.services.exception.ObjectNotFoundException;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private RequestItemRepository requestItemRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EmailService emailService;

	public Request find(Integer id) {
		Optional<Request> obj = requestRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Class: " + Request.class.getName()));
	}

	@Transactional
	public Request insert(Request request) {
		request.setId(null);
		request.setDate(new Date());
		request.setCustomer(customerService.find(request.getCustomer().getId()));
		request.getPayment().setStatus(PaymentStatus.PENDENTE);
		request.getPayment().setRequest(request);

		if (request.getPayment() instanceof PaymentWithBoleto) {
			PaymentWithBoleto payment = (PaymentWithBoleto) request.getPayment();
			boletoService.fillPaymentWithBoleto(payment, request.getDate());
		}

		request = requestRepository.save(request);
		paymentRepository.save(request.getPayment());

		for (RequestItem requestItem : request.getItems()) {
			requestItem.setDiscount(0.0);
			requestItem.setProduct(productService.find(requestItem.getProduct().getId()));
			requestItem.setPrice(requestItem.getProduct().getPrice());
			requestItem.setRequest(request);
		}

		requestItemRepository.saveAll(request.getItems());

		emailService.sendOrderConfirmatioHtmlEmail(request);

		return request;
	}

	public Page<Request> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Customer customer = customerService.find(user.getId());
		
		return requestRepository.findByCustomer(customer, pageRequest);
	}
}
