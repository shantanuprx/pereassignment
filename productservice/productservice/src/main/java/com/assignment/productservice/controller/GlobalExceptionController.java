package com.assignment.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.productservice.dto.ErrorDto;
import com.assignment.productservice.util.ResponseUtil;

@ControllerAdvice
@SuppressWarnings("unchecked")
public class GlobalExceptionController<T> {

	@Autowired
	private ResponseUtil<T> responseUtil;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<T> mainExceptionHandler(Exception ex) {
		return responseUtil.prepareResponse((T) new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
				ex.getLocalizedMessage(), System.currentTimeMillis()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
