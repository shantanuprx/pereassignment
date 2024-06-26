package com.assignment.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.productservice.dto.ProductDto;
import com.assignment.productservice.dto.ProductUpdateDto;
import com.assignment.productservice.service.ProductOperationService;

import jakarta.validation.Valid;

@RestController
@Validated
public class AppController<T> {

	@Autowired
	private ProductOperationService<T> productOperationService;

	@GetMapping("/fetch")
	public ResponseEntity<T> getDetaislsOfProduct(@RequestParam int productId) {
		return productOperationService.getDetails(productId);
	}

	@PostMapping("/add")
	public ResponseEntity<T> addProduct(@Valid @RequestBody ProductDto request, @RequestHeader String loggedInUser) {
		return productOperationService.addProduct(request, loggedInUser);
	}

	@PutMapping("/update")
	public ResponseEntity<T> updateProduct(@Valid @RequestBody ProductUpdateDto request, @RequestHeader String loggedInUser) {
		return productOperationService.updateProduct(request, loggedInUser);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<T> deleteProduct(@RequestParam int productId, @RequestHeader String loggedInUser) {
		return productOperationService.deleteProduct(productId, loggedInUser);
	}
}
