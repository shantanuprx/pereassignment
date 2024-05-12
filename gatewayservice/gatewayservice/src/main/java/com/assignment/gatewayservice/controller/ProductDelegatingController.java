package com.assignment.gatewayservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.gatewayservice.feignclients.ProductServiceFeignClient;

@RestController
@RequestMapping("/product")
public class ProductDelegatingController<T> {

	@Autowired
	private ProductServiceFeignClient<T> productServiceFeignClient;
	
	@GetMapping("/fetch")
	public ResponseEntity<T> getDetaislsOfProduct(@RequestParam int productId) {
		return productServiceFeignClient.getDetailsOfProduct(productId);
	}

	@PostMapping("/add")
	public ResponseEntity<T> addProduct(@RequestBody Map<String, Object> request, @RequestHeader String loggedInUser) {
		return productServiceFeignClient.addProduct(request, loggedInUser);
	}

	@PutMapping("/update")
	public ResponseEntity<T> updateProduct(@RequestBody Map<String, Object> request, @RequestHeader String loggedInUser) {
		return productServiceFeignClient.updateProduct(request, loggedInUser);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<T> deleteProduct(@RequestParam int productId, @RequestHeader String loggedInUser) {
		return productServiceFeignClient.deleteProduct(productId, loggedInUser);
	}
}
