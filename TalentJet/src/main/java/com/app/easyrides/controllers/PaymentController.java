package com.app.easyrides.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Payment;
import com.app.easyrides.services.PaymentService;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping
	public ResponseEntity<PagedResponseDto<Payment>> getPaymentList(
			@QuerydslPredicate(root = Payment.class) Predicate predicate, Pageable pageable) {
		log.info(">> getPaymentList({}, {})", predicate, pageable);
		return ResponseEntity.ok(paymentService.getPaymentList(predicate, pageable));
	}

	@PostMapping
	public ResponseEntity<Void> createPayment(@RequestBody Payment payment) {
		log.info(">> createPayment({})", payment);
		paymentService.createPayment(payment);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
		log.info(">> getPaymentById({})", id);
		return ResponseEntity.ok(paymentService.getPaymentById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCar(@PathVariable Long id, @RequestBody Payment payment) {
		log.info(">> updateCar({}, {})", id, payment);
		paymentService.updatePayment(id, payment);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
		log.info(">> deletePayment({})", id);
		paymentService.deletePayment(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
