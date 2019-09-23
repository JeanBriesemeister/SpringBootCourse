package com.jean.sbc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jean.sbc.domain.Address;
import com.jean.sbc.domain.Category;
import com.jean.sbc.domain.City;
import com.jean.sbc.domain.Customer;
import com.jean.sbc.domain.Product;
import com.jean.sbc.domain.Province;
import com.jean.sbc.domain.enums.CustomerType;
import com.jean.sbc.repositories.AddressRepository;
import com.jean.sbc.repositories.CategoryRepository;
import com.jean.sbc.repositories.CityRepository;
import com.jean.sbc.repositories.CustomerRepository;
import com.jean.sbc.repositories.ProductRepository;
import com.jean.sbc.repositories.ProvinceRepository;

@SpringBootApplication
public class SprintBootCourseApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ProvinceRepository provinceRepostory;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(SprintBootCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p3));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		Province pr1 = new Province(null, "Minas Gerais");
		Province pr2 = new Province(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", pr1);
		City c2 = new City(null, "São Paulo", pr2);
		City c3 = new City(null, "Campinas", pr2);

		pr1.getCities().add(c1);
		pr2.getCities().add(c2);
		pr2.getCities().add(c3);

		provinceRepostory.saveAll(Arrays.asList(pr1, pr2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Customer customer1 = new Customer(null, "Maria Silva", "maria@gmail.com", "122313", CustomerType.PESSAOFISICA);
		customer1.getTelephones().addAll((Arrays.asList("12321", "1231564")));

		Address ad1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "383232", customer1, c1);
		Address ad2 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "383232", customer1, c2);

		customer1.getAddresses().addAll(Arrays.asList(ad1, ad2));

		customerRepository.saveAll(Arrays.asList(customer1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
	}

}
