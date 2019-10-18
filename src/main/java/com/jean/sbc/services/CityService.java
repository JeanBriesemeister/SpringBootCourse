package com.jean.sbc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.sbc.domain.City;
import com.jean.sbc.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public List<City> findByProvince(Integer provinceId) {
		return cityRepository.findCities(provinceId);
	}
}
