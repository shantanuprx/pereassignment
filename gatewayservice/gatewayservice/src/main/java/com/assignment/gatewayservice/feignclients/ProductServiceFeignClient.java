package com.assignment.gatewayservice.feignclients;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("productservice")
public interface ProductServiceFeignClient<T> {

	@GetMapping("/product/fetch")
	public ResponseEntity<T> getDetailsOfProduct(int productId);

	@PostMapping("/product/add")
	public ResponseEntity<T> addProduct(Map<String, Object> requestMap, String loggedInUser);

	@PutMapping("/product/update")
	public ResponseEntity<T> updateProduct(Map<String, Object> requestMap, String loggedInUser);

	@DeleteMapping("/product/delete")
	public ResponseEntity<T> deleteProduct(int productId, String loggedInUser);
}
