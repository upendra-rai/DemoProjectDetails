package com.app.easyrides.dtos;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

	private int customerId;

	private String firstName;

	private String lastName;

	private String address;

	private String govtId;

	private LocalDate dateOfBirth;

	private String licenceId;

	private String contactNumber;

	private boolean accountStatus;

}
