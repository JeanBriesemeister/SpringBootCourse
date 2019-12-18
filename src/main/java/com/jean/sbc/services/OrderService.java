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
import com.jean.sbc.domain.Order;
import com.jean.sbc.domain.OrderItem;
import com.jean.sbc.domain.PaymentWithBankSlip;
import com.jean.sbc.domain.enums.PaymentStatus;
import com.jean.sbc.repositories.OrderItemRepository;
import com.jean.sbc.repositories.OrderRepository;
import com.jean.sbc.repositories.PaymentRepository;
import com.jean.sbc.security.UserSS;
import com.jean.sbc.services.exception.AuthorizationException;
import com.jean.sbc.services.exception.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private BankSlipService bankSlipService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EmailService emailService;

	public Order find(Integer id) {
		Optional<Order> obj = orderRepository.findById(id);

		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Class: " + Order.class.getName()));
	}

	@Transactional
	public Order insert(Order order) {
		order.setId(null);
		order.setDate(new Date());
		order.setCustomer(customerService.find(order.getCustomer().getId()));
		order.getPayment().setStatus(PaymentStatus.PENDING);
		order.getPayment().setOrder(order);

		if (order.getPayment() instanceof PaymentWithBankSlip) {
			PaymentWithBankSlip payment = (PaymentWithBankSlip) order.getPayment();
			bankSlipService.fillPaymentWithBankSlip(payment, order.getDate());
		}

		order = orderRepository.save(order);
		paymentRepository.save(order.getPayment());

		for (OrderItem orderItem : order.getItems()) {
			orderItem.setDiscount(0.0);
			orderItem.setProduct(productService.find(orderItem.getProduct().getId()));
			orderItem.setPrice(orderItem.getProduct().getPrice());
			orderItem.setOrder(order);
		}

		orderItemRepository.saveAll(order.getItems());

		emailService.sendOrderConfirmatioHtmlEmail(order);

		return order;
	}

	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access denied");
		}

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Customer customer = customerService.find(user.getId());

		return orderRepository.findByCustomer(customer, pageRequest);
	}
}
