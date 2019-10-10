package com.jean.sbc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.Customer;
import com.jean.sbc.repositories.CustomerRepository;
import com.jean.sbc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(email);

		if (customer == null) {
			throw new UsernameNotFoundException(email);
		}

		return new UserSS(customer.getId(), customer.getEmail(), customer.getPassword(), customer.getProfiles());
	}

}
