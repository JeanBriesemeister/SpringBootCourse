package com.jean.sbc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jean.sbc.domain.Address;
import com.jean.sbc.domain.Category;
import com.jean.sbc.domain.City;
import com.jean.sbc.domain.Customer;
import com.jean.sbc.domain.Payment;
import com.jean.sbc.domain.PaymentWithBoleto;
import com.jean.sbc.domain.PaymentWithCard;
import com.jean.sbc.domain.Product;
import com.jean.sbc.domain.Province;
import com.jean.sbc.domain.Request;
import com.jean.sbc.domain.RequestItem;
import com.jean.sbc.domain.enums.CustomerType;
import com.jean.sbc.domain.enums.PaymentStatus;
import com.jean.sbc.repositories.AddressRepository;
import com.jean.sbc.repositories.CategoryRepository;
import com.jean.sbc.repositories.CityRepository;
import com.jean.sbc.repositories.CustomerRepository;
import com.jean.sbc.repositories.PaymentRepository;
import com.jean.sbc.repositories.ProductRepository;
import com.jean.sbc.repositories.ProvinceRepository;
import com.jean.sbc.repositories.RequestItemRepository;
import com.jean.sbc.repositories.RequestRepository;

@SpringBootApplication
public class SprintBootCourseApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ProvinceRepository provinceRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private RequestItemRepository requestItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(SprintBootCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		Province est1 = new Province(null, "Minas Gerais");
		Province est2 = new Province(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));

		provinceRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Customer cli1 = new Customer(null, "Maria Silva", "maria@gmail.com", "36378912377", CustomerType.PESSOAFISICA);

		cli1.getTelephones().addAll(Arrays.asList("27363323", "93838393"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(e1, e2));

		customerRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Request ped1 = new Request(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Request ped2 = new Request(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pagto1 = new PaymentWithCard(null, PaymentStatus.QUITADO, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new PaymentWithBoleto(null, PaymentStatus.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getRequests().addAll(Arrays.asList(ped1, ped2));

		requestRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));

		RequestItem ip1 = new RequestItem(ped1, p1, 0.00, 1, 2000.00);
		RequestItem ip2 = new RequestItem(ped1, p3, 0.00, 2, 80.00);
		RequestItem ip3 = new RequestItem(ped2, p2, 100.00, 1, 800.00);

		ped1.getItems().addAll(Arrays.asList(ip1, ip2));
		ped2.getItems().addAll(Arrays.asList(ip3));

		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p1.getItems().addAll(Arrays.asList(ip2));

		requestItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
