package com.jean.sbc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.Customer;
import com.jean.sbc.repositories.CustomerRepository;
import com.jean.sbc.services.exception.ObjectNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer find(Integer id) {
		Optional<Customer> obj = customerRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Class: " + Customer.class.getName()));
	}
}
