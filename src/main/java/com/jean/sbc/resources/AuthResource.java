package com.jean.sbc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jean.sbc.dto.EmailDTO;
import com.jean.sbc.security.JWTUtil;
import com.jean.sbc.security.UserSS;
import com.jean.sbc.services.AuthService;
import com.jean.sbc.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDto) {
		authService.sendNewPassword(emailDto.getEmail());

		return ResponseEntity.noContent().build();
	}
}
