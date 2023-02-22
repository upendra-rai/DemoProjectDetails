package com.app.easyrides.entities;

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
@Table(name = "car_owner")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarOwner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_ownerId", unique = true, nullable = false)
	private Long  carOwnerId;
	
	@Column(name = "owner_name", length = 50, nullable = false)
	private String ownerName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@Column(name = "contact_number", length = 50, nullable = false)
	private String contactNumber;

}
