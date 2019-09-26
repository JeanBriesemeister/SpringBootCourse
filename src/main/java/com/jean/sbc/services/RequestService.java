package com.jean.sbc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.Request;
import com.jean.sbc.repositories.RequestRepository;
import com.jean.sbc.services.exception.ObjectNotFoundException;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;

	public Request find(Integer id) {
		Optional<Request> obj = requestRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Class: " + Request.class.getName()));
	}
}
