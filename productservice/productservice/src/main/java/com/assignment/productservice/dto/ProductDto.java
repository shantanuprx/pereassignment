package com.assignment.productservice.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
/* *
 * Product DTO for handling inbound and outbound data.
 * */
@Data
@JsonInclude(value = Include.NON_NULL)
public class ProductDto {

	private BigInteger productId;

	private String productName;

	private String productDescription;

	private int currentStock;

	private String status;

	private String isDeleted;

	private String seller;

	private String sellerAddress;

	private BigDecimal price;

	private BigDecimal oldPrice;

	private Timestamp createdTimestamp;

	private Timestamp updatedTimestamp;

	private String createdBy;

	private String updatedBy;
}
