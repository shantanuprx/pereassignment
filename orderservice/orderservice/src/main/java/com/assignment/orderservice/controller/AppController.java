package com.assignment.orderservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.service.OrderOperationService;

import jakarta.validation.Valid;

@RestController
@Validated
public class AppController<T> {

	@Autowired
	private OrderOperationService<T> orderOperationService;
	
	@GetMapping("/fetch")
	public ResponseEntity<T> getDetaislsOfProduct(@RequestBody Map<String, Object> requestMap) {
		return orderOperationService.getDetails(requestMap);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<T> getDetailsOfProduct(@RequestBody Map<String, Object> requestMap) {
		return orderOperationService.getDetails(requestMap);
	}
	
	@PostMapping("/add")
	public ResponseEntity<T> addProduct(@Valid @RequestBody OrdersDto request) {
		return orderOperationService.createOrder(request);
	} 
	
	@PutMapping("/update")
	public ResponseEntity<T> updateProduct(@Valid @RequestBody OrdersDto request) {
		return orderOperationService.updateOrder(request);
	}
	
}
