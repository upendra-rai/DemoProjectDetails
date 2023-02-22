package com.app.easyrides.services;

import org.springframework.data.domain.Pageable;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Booking;
import com.querydsl.core.types.Predicate;

public interface BookingService {
	
	PagedResponseDto<Booking> getBookingList(Predicate predicate, Pageable pageable);

	void createBooking(Booking booking);

	Booking getBookingById(Long id);

	void updateBooking(Long id, Booking booking);

	void deleteBooking(Long id);

}
