package com.assignment.productservice.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ProductUpdateDto {

	private int productId;

	private String productName;

	private String productDescription;

	private int currentStock;

	private String status;

	private String seller;

	private String sellerAddress;

	private BigDecimal price;

}