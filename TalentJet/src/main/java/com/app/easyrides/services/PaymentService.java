package com.app.easyrides.services;

import org.springframework.data.domain.Pageable;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Payment;
import com.querydsl.core.types.Predicate;

public interface PaymentService {
	PagedResponseDto<Payment> getPaymentList(Predicate predicate, Pageable pageable);

	void createPayment(Payment payment);

	Payment getPaymentById(Long id);

	void updatePayment(Long id, Payment payment);

	void deletePayment(Long id);

}
