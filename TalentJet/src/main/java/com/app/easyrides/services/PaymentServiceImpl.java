package com.app.easyrides.services;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Booking;
import com.app.easyrides.entities.Payment;
import com.app.easyrides.exception.ErrorCode;
import com.app.easyrides.exception.ServiceException;
import com.app.easyrides.repositories.BookingRepository;
import com.app.easyrides.repositories.PaymentRepository;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	@Transactional
	public PagedResponseDto<Payment> getPaymentList(Predicate predicate, Pageable pageable) {
		log.info(">> getPaymentList({}, {})", predicate, pageable);
		Page<Payment> carOwnerPage = paymentRepository.findAll(predicate, pageable);
		return PagedResponseDto.<Payment>builder()
				.list(carOwnerPage.stream().map(carOwner -> carOwner).collect(Collectors.toList()))
				.page(carOwnerPage.getNumber()).size(carOwnerPage.getSize())
				.totalElements(carOwnerPage.getTotalElements()).build();
	}

	@Override
	public void createPayment(Payment payment) {
		log.info(">> createPayment({})", payment);
		Booking booking = bookingRepository.findById(payment.getBooking().getBookingId()).orElseThrow(
				() -> new ServiceException(ErrorCode.BOOKING_NOT_FOUND, payment.getBooking().getBookingId()));
		payment.setBooking(booking);
		paymentRepository.save(payment);
	}

	@Override
	public Payment getPaymentById(Long id) {
		log.info(">> getPaymentById({})", id);
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.PAYMENT_NOT_FOUND, id));
		return payment;
	}

	@Override
	public void updatePayment(Long id, Payment payment) {
		log.info(">> updatePayment({})", id, payment);
		Payment updatePayment = paymentRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.PAYMENT_NOT_FOUND, id));
		Booking booking = bookingRepository.findById(payment.getBooking().getBookingId()).orElseThrow(
				() -> new ServiceException(ErrorCode.BOOKING_NOT_FOUND, payment.getBooking().getBookingId()));
		updatePayment.setBooking(booking);
		updatePayment.setPaymentDate(payment.getPaymentDate());
		updatePayment.setPaymentType(payment.getPaymentType());
		updatePayment.setPaymentAmount(payment.getPaymentAmount());
		paymentRepository.save(updatePayment);
	}

	@Override
	public void deletePayment(Long id) {
		log.info(">> deletePayment({})", id);
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.PAYMENT_NOT_FOUND, id));
		paymentRepository.delete(payment);
	}

}
