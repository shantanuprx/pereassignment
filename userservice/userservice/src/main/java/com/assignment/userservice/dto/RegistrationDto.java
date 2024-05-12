package com.assignment.userservice.dto;

import java.math.BigInteger;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationDto {

	private BigInteger userId;

    private String firstName;

    private String midName;

    private String lastName;

    private String emailId;

    private String mobileNumber;

    private Date dateOfBirth;

}
