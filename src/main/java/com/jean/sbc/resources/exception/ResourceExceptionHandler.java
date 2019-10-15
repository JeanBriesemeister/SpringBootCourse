package com.jean.sbc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.jean.sbc.services.exception.AuthorizationException;
import com.jean.sbc.services.exception.DataIntegrityException;
import com.jean.sbc.services.exception.FileException;
import com.jean.sbc.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandarError error = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandarError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		StandarError error = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandarError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation error",
				System.currentTimeMillis());

		for (FieldError er : e.getBindingResult().getFieldErrors()) {
			error.addError(er.getField(), er.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandarError> authorization(AuthorizationException e, HttpServletRequest request) {
		StandarError error = new StandarError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandarError> file(FileException e, HttpServletRequest request) {
		StandarError error = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandarError> amazonServiceException(AmazonServiceException e, HttpServletRequest request) {
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());

		StandarError error = new StandarError(code.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(code).body(error);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandarError> amazonClientException(AmazonClientException e, HttpServletRequest request) {
		StandarError error = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandarError> amazonS3Exception(AmazonS3Exception e, HttpServletRequest request) {
		StandarError error = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
