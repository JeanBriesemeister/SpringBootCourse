package com.jean.sbc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.Province;
import com.jean.sbc.repositories.ProvinceRepository;

@Service
public class ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;

	public List<Province> findAll() {
		return provinceRepository.findAllByOrderByName();
	}
}
