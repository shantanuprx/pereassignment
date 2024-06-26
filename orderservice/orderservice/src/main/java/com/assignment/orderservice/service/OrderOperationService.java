package com.assignment.orderservice.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.orderservice.adapters.OrdersAdapter;
import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.ErrorDto;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.dto.ResponseDto;
import com.assignment.orderservice.entity.Orders;
import com.assignment.orderservice.repository.OrdersRepository;
import com.assignment.orderservice.util.ResponseUtil;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unchecked")
@Service
@Slf4j
public class OrderOperationService<T> {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private ResponseUtil<T> responseUtil;

	@Value("${order.page.pagesize:10}")
	private int pageSize;

	public ResponseEntity<T> updateOrder(@Valid OrdersDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<T> createOrder(@Valid OrdersDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<T> getDetails(Map<String, Object> requestMap) {
		log.info("Entering getDetails Method at {} ", System.currentTimeMillis());
		try {
			if (requestMap.containsKey("orderId")) {
				Optional<Orders> orderOptionalEntity = ordersRepository
						.findByOrderIdAndUserId((int) requestMap.get("orderId"), (int) requestMap.get("userId"));
				if (orderOptionalEntity.isEmpty()) {
					return responseUtil.prepareResponse(
							(T) new ResponseDto(HttpStatus.BAD_REQUEST, OrdersConstant.INVALID_PRODUCT_ID),
							HttpStatus.BAD_REQUEST);
				}
				Orders productEntity = orderOptionalEntity.get();
				OrdersDto productDto = OrdersAdapter.convertEntityToModel(productEntity);
				log.info("Product {} successfully fetched ", productEntity.getProductId());
				return responseUtil.prepareResponse((T) productDto, HttpStatus.OK);
			} else if (requestMap.containsKey("pageNumber")) {
				List<Orders> orderEntities = ordersRepository.findAllByUserId((int) requestMap.get("userId"),
						PageRequest.of((int) requestMap.get("pageNumber"), pageSize));
				List<OrdersDto> orderList = orderEntities.stream().map(a -> OrdersAdapter.convertEntityToModel(a))
						.collect(Collectors.toList());
				return responseUtil.prepareResponse((T) orderList, HttpStatus.OK);
			} else {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, OrdersConstant.PROVIDE_PAGE_NUMBER_OR_ORDER_ID),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Exception occurred while fetching product details with exception ", ex);
			return responseUtil
					.prepareResponse(
							(T) new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
									ex.getLocalizedMessage(), System.currentTimeMillis()),
							HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			log.info("Exiting getDetails method at  {} ", System.currentTimeMillis());
		}
	}
}
