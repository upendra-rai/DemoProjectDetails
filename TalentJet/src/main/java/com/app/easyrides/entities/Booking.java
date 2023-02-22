package com.app.easyrides.entities;

import java.time.LocalDate;

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
@Table(name = "booking")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookingId", unique = true, nullable = false)
	private Long  bookingId;
	
	@OneToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;
	
	@Column(name = "pickup_date", length = 50, nullable = false)
	private LocalDate pickupDate;
	
	@Column(name = "drop_date", length = 50, nullable = false)
	private LocalDate dropDate;
	
	@OneToOne
	@JoinColumn(name = "pickup_address_id", nullable = false)
	private Address pickupAddress;
	
	@OneToOne
	@JoinColumn(name = "drop_address_id", nullable = false)
	private Address dropAddress;
	
	@Column(name = "booking_status", length = 50)
	private boolean bookingStatus;
	

}
