package com.jean.sbc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.Customer;
import com.jean.sbc.dto.CustomerDTO;
import com.jean.sbc.repositories.CustomerRepository;
import com.jean.sbc.services.exception.DataIntegrityException;
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

	public Customer update(Customer customer) {
		Customer customerDB = this.find(customer.getId());
		updateData(customerDB, customer);
		return customerRepository.save(customerDB);
	}

	private void updateData(Customer customerDB, Customer customer) {
		customerDB.setName(customer.getName());
		customerDB.setEmail(customer.getEmail());
	}

	public void delete(Integer id) {
		try {
			customerRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Customer has related entities!");
		}
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String orderByDirection) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(orderByDirection), orderBy);

		return customerRepository.findAll(pageRequest);
	}

	public Customer fromDTO(CustomerDTO customerDTO) {
		return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(), null, null);
	}
}
