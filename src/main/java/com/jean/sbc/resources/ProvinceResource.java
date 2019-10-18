package com.jean.sbc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jean.sbc.domain.City;
import com.jean.sbc.domain.Province;
import com.jean.sbc.dto.CityDTO;
import com.jean.sbc.dto.ProvinceDTO;
import com.jean.sbc.services.CityService;
import com.jean.sbc.services.ProvinceService;

@RestController
@RequestMapping(value = "/provinces")
public class ProvinceResource {

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private CityService cityService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProvinceDTO>> findAll() {
		List<Province> list = provinceService.findAll();
		List<ProvinceDTO> listDTO = list.stream().map(obj -> new ProvinceDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/{provinceId}/cities", method = RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer provinceId) {
		List<City> list = cityService.findByProvince(provinceId);
		List<CityDTO> listDTO = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
}
