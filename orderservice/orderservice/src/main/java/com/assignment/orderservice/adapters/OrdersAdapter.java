package com.assignment.orderservice.adapters;

import java.io.IOException;

import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.entity.Orders;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrdersAdapter {

	private static ThreadLocal<ObjectMapper> THREAD_LOCAL_OBJECT_MAPPER = new ThreadLocal<>();

	public static OrdersDto convertEntityToModel(Orders productEntity) {
		try {
			return THREAD_LOCAL_OBJECT_MAPPER.get()
					.readValue(THREAD_LOCAL_OBJECT_MAPPER.get().writeValueAsBytes(productEntity), OrdersDto.class);
		} catch (IOException e) {
			return null;
		}
	}

}
