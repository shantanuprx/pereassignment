package com.assignment.userservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.userservice.util.ServiceLocator;

@RestController
public class AppController<T> {

	@Autowired
	private ServiceLocator<T> serviceLocator;

	@GetMapping("/fetch/{service}")
	public ResponseEntity<T> getDetails(@RequestBody Map<String, Object> requestBody, @PathVariable String service) {
		return serviceLocator.locateServiceBean(service).getDetails(requestBody);
	}

	@PostMapping("/add/{service}")
	public ResponseEntity<T> addDetails(@RequestBody Map<String, Object> requestBody, @PathVariable String service) {
		return serviceLocator.locateServiceBean(service).addDetails(requestBody);
	}

	@PutMapping("/update/{service}")
	public ResponseEntity<T> updateDetails(@RequestBody Map<String, Object> requestBody, @PathVariable String service) {
		return serviceLocator.locateServiceBean(service).updateDetails(requestBody);
	}

	@DeleteMapping("/delete/{service}")
	public ResponseEntity<T> deleteDetails(@RequestBody Map<String, Object> requestBody, @PathVariable String service) {
		return serviceLocator.locateServiceBean(service).deleteDetails(requestBody);
	}
}
