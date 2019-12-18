package com.jean.sbc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jean.sbc.domain.Address;
import com.jean.sbc.domain.City;
import com.jean.sbc.domain.Customer;
import com.jean.sbc.domain.enums.CustomerType;
import com.jean.sbc.domain.enums.Profile;
import com.jean.sbc.dto.CustomerDTO;
import com.jean.sbc.dto.CustomerNewDTO;
import com.jean.sbc.repositories.AddressRepository;
import com.jean.sbc.repositories.CustomerRepository;
import com.jean.sbc.security.UserSS;
import com.jean.sbc.services.exception.AuthorizationException;
import com.jean.sbc.services.exception.DataIntegrityException;
import com.jean.sbc.services.exception.ObjectNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;

	public Customer find(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied");
		}

		Optional<Customer> obj = customerRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Class: " + Customer.class.getName()));
	}

	@Transactional
	public Customer insert(Customer customer) {
		customer.setId(null);
		customer = customerRepository.save(customer);

		addressRepository.saveAll(customer.getAddresses());

		return customer;
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
			throw new DataIntegrityException("Customer has orders!");
		}
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Access denied");
		}

		return this.find(user.getId());
	}

	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String orderByDirection) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(orderByDirection), orderBy);

		return customerRepository.findAll(pageRequest);
	}

	public Customer fromDTO(CustomerDTO customerDTO) {
		return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(), null, null, null);
	}

	public Customer fromDTO(CustomerNewDTO customerDTO) {
		Customer customer = new Customer(null, customerDTO.getName(), customerDTO.getEmail(),
				customerDTO.getSocialInsuranceOrBusinessNumber(), CustomerType.toEnum(customerDTO.getCustomerType()),
				pe.encode(customerDTO.getPassword()));

		City city = new City(customerDTO.getCityId(), null, null);

		Address address = new Address(null, customerDTO.getStreet(), customerDTO.getNumber(),
				customerDTO.getComplement(), customerDTO.getDistrict(), customerDTO.getPostalCode(), customer, city);

		customer.getAddresses().add(address);
		customer.getTelephones().add(customerDTO.getTelephone1());
		if (customerDTO.getTelephone2() != null) {
			customer.getTelephones().add(customerDTO.getTelephone2());
		}
		if (customerDTO.getTelephone3() != null) {
			customer.getTelephones().add(customerDTO.getTelephone3());
		}
		return customer;

	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access denied");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
