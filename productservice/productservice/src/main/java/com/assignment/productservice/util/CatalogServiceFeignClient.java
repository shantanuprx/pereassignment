package com.assignment.productservice.util;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("catalogservice")
public interface CatalogServiceFeignClient<T> {

	@PostMapping("/catalog/add")
	public ResponseEntity<T> addDetails(Map<String, Object> requestBody);
	
	@PutMapping("/catalog/update")
	public ResponseEntity<T> updateDetails(Map<String, Object> requestBody);

	@DeleteMapping("/catalog/delete")
	public ResponseEntity<T> deleteDetails(int productId);
}
