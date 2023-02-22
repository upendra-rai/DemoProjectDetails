package com.app.easyrides.entities;

import java.time.LocalDateTime;

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
@Table(name = "rent_car")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RentCar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rent_carId", unique = true, nullable = false)
	private Long rentCarId;

	@Column(name = "current_location", length = 255)
	private String currentLocation;

	@Column(name = "drop_location", length = 50)
	private String dropLocation;

	@Column(name = "start_Date_time", length = 50)
	private LocalDateTime startDateTime;

	@Column(name = "end_Date_time", length = 50)
	private LocalDateTime endDateTime;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "car_id")
	private Car car;

	@Column(name = "trip_type", length = 50)
	private String tripType;

}
