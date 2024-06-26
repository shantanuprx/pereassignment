package com.assignment.productservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.productservice.adapters.ProductAdapter;
import com.assignment.productservice.constants.ProductsConstants;
import com.assignment.productservice.dto.ProductDto;
import com.assignment.productservice.dto.ProductUpdateDto;
import com.assignment.productservice.dto.ResponseDto;
import com.assignment.productservice.entity.Product;
import com.assignment.productservice.repository.ProductRepository;
import com.assignment.productservice.util.ElasticSearchUtil;
import com.assignment.productservice.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class ProductOperationService<T> {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ElasticSearchUtil<T> elasticSearchUtil;

	@Autowired
	private ResponseUtil<T> responseUtil;

	public ResponseEntity<T> getDetails(int productId) {
		log.info("Entering getDetails Method at {} ", System.currentTimeMillis());
		try {
			Optional<Product> productEntityOptional = productRepository.findById(productId);
			if (productEntityOptional.isEmpty()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, ProductsConstants.INVALID_PRODUCT_ID),
						HttpStatus.BAD_REQUEST);
			}
			Product productEntity = productEntityOptional.get();
			ProductDto productDto = ProductAdapter.convertEntityToModel(productEntity);
			log.info("Product {} successfully fetched ", productEntity.getProductId());
			return responseUtil.prepareResponse((T) productDto, HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while fetching product details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting getDetails method at  {} ", System.currentTimeMillis());
		}
	}

	public ResponseEntity<T> addProduct(ProductDto request, String loggedInUser) {
		log.info("Entering addProduct Method at {} ", System.currentTimeMillis());
		try {
			Product productEntityToPersist = ProductAdapter.convertModelToEntityForInsertion(request);
			productEntityToPersist.setCreatedBy(loggedInUser);
			productRepository.save(productEntityToPersist);
			elasticSearchUtil.indexObject(request);
			log.info("Product {} successfully created by {} ", productEntityToPersist.getProductId(), loggedInUser);
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.CREATED, ProductsConstants.RECORD_CREATION_MESSAGE),
					HttpStatus.CREATED);
		} catch (Exception ex) {
			log.error("Exception occurred while adding new product with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting addProduct method at  {} ", System.currentTimeMillis());
		}
	}

	public ResponseEntity<T> updateProduct(ProductUpdateDto request, String loggedInUser) {
		log.info("Entering updateProduct Method at {} ", System.currentTimeMillis());
		try {
			Optional<Product> productEntityOptional = productRepository.findById(request.getProductId());
			if (productEntityOptional.isEmpty()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, ProductsConstants.INVALID_PRODUCT_ID),
						HttpStatus.BAD_REQUEST);
			}
			Product productEntity = productEntityOptional.get();

			ProductAdapter.mapModelValuesToEntityForUpdate(request, productEntity);
			productEntity.setUpdatedBy(loggedInUser);
			productRepository.save(productEntity);
			elasticSearchUtil.updateObject(ProductAdapter.convertEntityToModel(productEntity));
			log.info("Product {} successfully updated by {} ", productEntity.getProductId(), loggedInUser);
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.OK, ProductsConstants.RECORD_UPDATE_MESSAGE), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while updating product with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting updateProduct method at  {} ", System.currentTimeMillis());
		}
	}

	public ResponseEntity<T> deleteProduct(int productId, String loggedInUser) {
		log.info("Entering deleteProduct Method at {} ", System.currentTimeMillis());
		try {
			Optional<Product> productEntityOptional = productRepository.findById(productId);
			if (productEntityOptional.isEmpty()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, ProductsConstants.INVALID_PRODUCT_ID),
						HttpStatus.BAD_REQUEST);
			}
			Product productEntity = productEntityOptional.get();
			ProductAdapter.updateEntityValuesForDeletion(productEntity);
			productEntity.setUpdatedBy(loggedInUser);
			productRepository.save(productEntity);
			elasticSearchUtil.deleteItemFromIndex(productEntity.getProductId());
			log.info("Product {} successfully deleted by {} ", productEntity.getProductId(), loggedInUser);
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.OK, ProductsConstants.RECORD_CREATION_MESSAGE), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while deleting the product with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting deleteProduct method at  {} ", System.currentTimeMillis());
		}
	}

}
