package com.app.easyrides.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "address")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addressId", unique = true, nullable = false)
	private Long addressId;

	@Column(name = "address_line1", length = 50)
	private String line1;

	@Column(name = "address_line2", length = 50)
	private String line2;

	@Column(name = "address_line3", length = 50)
	private String line3;

	@Column(name = "city", length = 50)
	private String city;

	@Column(name = "state", length = 50)
	private String state;

	@Column(name = "zipCode", length = 50)
	private String zipCode;

}
