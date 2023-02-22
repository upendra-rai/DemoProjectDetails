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
import com.app.easyrides.entities.Booking;
import com.app.easyrides.services.BookingService;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping
	public ResponseEntity<PagedResponseDto<Booking>> getBookingList(
			@QuerydslPredicate(root = Booking.class) Predicate predicate, Pageable pageable) {
		log.info(">> getBookingList({}, {})", predicate, pageable);
		return ResponseEntity.ok(bookingService.getBookingList(predicate, pageable));
	}
	
	@PostMapping
	public ResponseEntity<Void> createBooking(@RequestBody Booking booking) {
		bookingService.createBooking(booking);
		log.info(">> createBooking({})", booking);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
		log.info(">> getBookingById({})", id);
		return ResponseEntity.ok(bookingService.getBookingById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
		bookingService.updateBooking(id, booking);
		log.info(">> updateBooking({}, {})", id,booking);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
		log.info(">> deleteBooking({})", id);
		bookingService.deleteBooking(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
