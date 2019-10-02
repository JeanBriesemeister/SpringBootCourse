package com.jean.sbc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jean.sbc.domain.Product;
import com.jean.sbc.dto.ProductDTO;
import com.jean.sbc.resources.utils.URL;
import com.jean.sbc.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) {
		Product product = productService.find(id);

		return ResponseEntity.ok().body(product);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "orderByDirection", defaultValue = "ASC") String orderByDirection) {

		String nameDecoded = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categories);

		Page<Product> products = productService.search(nameDecoded, ids, page, linesPerPage, orderBy, orderByDirection);

		Page<ProductDTO> productDto = products.map(product -> new ProductDTO(product));

		return ResponseEntity.ok().body(productDto);
	}
}
