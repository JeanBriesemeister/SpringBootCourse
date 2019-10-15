package com.jean.sbc.resources;

import java.net.URI;

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

import com.jean.sbc.domain.Request;
import com.jean.sbc.services.RequestService;

@RestController
@RequestMapping(value = "/requests")
public class RequestResource {

	@Autowired
	private RequestService requestService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Request> find(@PathVariable Integer id) {
		Request request = requestService.find(id);

		return ResponseEntity.ok().body(request);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Request request) {
		request = requestService.insert(request);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<Request>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "date") String orderBy,
			@RequestParam(value = "orderByDirection", defaultValue = "DESC") String orderByDirection) {

		Page<Request> categories = requestService.findPage(page, linesPerPage, orderBy, orderByDirection);

		return ResponseEntity.ok().body(categories);
	}
}
