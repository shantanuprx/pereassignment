package com.assignment.orderservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {

	private int orderId;

    private int productId;

    private int userId;

    private int paymentId;

    private String paymentSource;

    private int addressId;

    private String paymentType;

    private String refundStatus;

    private String orderStatus;

    private String paymentStatus;

    private Date createTimestamp;

    private Date updateTimestamp;

    private String createdBy;

    private String updatedBy;
}
