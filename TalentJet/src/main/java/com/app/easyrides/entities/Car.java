package com.app.easyrides.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "car")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carId", unique = true, nullable = false)
	private Long  carId;
	
	@Column(name = "car_name", length = 50, nullable = false)
	private String carName;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "car_model_year", length = 50, nullable = false)
	private String carModelYear;
	
	@Column(name = "car_brand", length = 150, nullable = false)
	private String carBrand;
	
	@Column(name = "color", length = 15, nullable = false)
	private String color;
	  
	@Column(name = "capacity", length = 50)
	private String capacity;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String  imagefile;
	
	@Column(name = "plate_number", length = 50, nullable = false)
	private String plateNumber;
	
	@Column(name = "rate", length = 50, nullable = false)
	private double rate;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private CarOwner carOwner;
	
	@Column(name = "status", length = 50)
	private String status;
	
	@Column(name = "is_active", length = 50)
	private boolean isActive;
	
}
