package com.assignment.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {

	private int id;

    private int userId;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String pincode;

}
