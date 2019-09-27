package com.jean.sbc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jean.sbc.domain.Customer;
import com.jean.sbc.dto.CustomerDTO;
import com.jean.sbc.dto.CustomerNewDTO;
import com.jean.sbc.services.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> find(@PathVariable Integer id) {
		Customer Customer = customerService.find(id);

		return ResponseEntity.ok().body(Customer);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CustomerNewDTO customerDTO) {
		Customer customer = customerService.fromDTO(customerDTO);
		customer = customerService.insert(customer);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable Integer id) {
		Customer customer = customerService.fromDTO(customerDTO);
		customer.setId(id);
		customer = customerService.update(customer);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		customerService.delete(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<Customer> customers = customerService.findAll();

		List<CustomerDTO> customersDto = customers.stream().map(customer -> new CustomerDTO(customer))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(customersDto);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CustomerDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "orderByDirection", defaultValue = "ASC") String orderByDirection) {

		Page<Customer> customers = customerService.findPage(page, linesPerPage, orderBy, orderByDirection);

		Page<CustomerDTO> customersDto = customers.map(customer -> new CustomerDTO(customer));

		return ResponseEntity.ok().body(customersDto);
	}
}
