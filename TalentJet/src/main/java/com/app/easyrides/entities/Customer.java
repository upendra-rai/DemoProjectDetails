package com.app.easyrides.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customer")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerId", unique = true, nullable = false)
	private Long customerId;

	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = false )
	private Address address;

	@Column(name = "govtId", length = 20)
	private String govtId;

	@Column(name = "date_of_birth", length = 20, nullable = false)
	private LocalDate dateOfBirth;

	@Column(name = "licenceId", length = 20, nullable = false)
	private String licenceId;

	@Column(name = "contact_number", length = 20, nullable = false)
	private String contactNumber;

	private boolean accountStatus;

}
