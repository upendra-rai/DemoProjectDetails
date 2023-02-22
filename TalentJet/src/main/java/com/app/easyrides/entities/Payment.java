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
@Table(name = "payment")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paymentId", unique = true, nullable = false)
	private Long  paymentId;
	
	@OneToOne
	@JoinColumn(name = "booking_id", nullable = false)
	private Booking booking;
	
	@Column(name = "payment_date", length = 50, nullable = false)
	private LocalDate paymentDate;
	
	@Column(name = "payment_type", length = 50)
	private String paymentType;
	
	@Column(name = "payment_amount", length = 50, nullable = false)
	private String paymentAmount;
	
	


}
