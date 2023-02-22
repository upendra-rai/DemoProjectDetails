package com.app.easyrides.services;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Address;
import com.app.easyrides.entities.Booking;
import com.app.easyrides.entities.Car;
import com.app.easyrides.entities.Customer;
import com.app.easyrides.exception.ErrorCode;
import com.app.easyrides.exception.ServiceException;
import com.app.easyrides.repositories.AddressRepository;
import com.app.easyrides.repositories.BookingRepository;
import com.app.easyrides.repositories.CarRepository;
import com.app.easyrides.repositories.CustomerRepository;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public PagedResponseDto<Booking> getBookingList(Predicate predicate, Pageable pageable) {
		   log.info(">> getBookingList({}, {})", predicate, pageable);
		Page<Booking> carOwnerPage = bookingRepository.findAll(predicate, pageable);
		return PagedResponseDto.<Booking>builder()
				.list(carOwnerPage.stream().map(carOwner -> carOwner).collect(Collectors.toList()))
				.page(carOwnerPage.getNumber()).size(carOwnerPage.getSize())
				.totalElements(carOwnerPage.getTotalElements()).build();
	}

	@Override
	public void createBooking(Booking booking) {
		 log.info(">> createBooking({})", booking);
		Customer customer = customerRepository.findById(booking.getCustomer().getCustomerId()).orElseThrow(
				() -> new ServiceException(ErrorCode.CUSTOMER_NOT_FOUND, booking.getCustomer().getCustomerId()));
		Car car = carRepository.findById(booking.getCar().getCarId())
				.orElseThrow(() -> new ServiceException(ErrorCode.CAR_NOT_FOUND, booking.getCar().getCarId()));
		Address pickUpAddress = addressRepository.findById(booking.getPickupAddress().getAddressId()).orElseThrow(
				() -> new ServiceException(ErrorCode.ADDRESS_NOT_FOUND, booking.getPickupAddress().getAddressId()));
		Address dropAddress = addressRepository.findById(booking.getDropAddress().getAddressId()).orElseThrow(
				() -> new ServiceException(ErrorCode.ADDRESS_NOT_FOUND, booking.getDropAddress().getAddressId()));
		car.setActive(false);
		booking.setCustomer(customer);
		booking.setCar(car);
		booking.setPickupAddress(pickUpAddress);
		booking.setDropAddress(dropAddress);
		bookingRepository.save(booking);
	}

	@Override
	public void updateBooking(Long id, Booking booking) {
		log.info(">> updateBooking({}, {})", id, booking);
		Booking updateBooking = bookingRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.BOOKING_NOT_FOUND, id));
		Customer customer = customerRepository.findById(booking.getCustomer().getCustomerId()).orElseThrow(
				() -> new ServiceException(ErrorCode.CUSTOMER_NOT_FOUND, booking.getCustomer().getCustomerId()));
		Car car = carRepository.findById(booking.getCar().getCarId())
				.orElseThrow(() -> new ServiceException(ErrorCode.CAR_NOT_FOUND, booking.getCar().getCarId()));
		Address pickUpAddress = addressRepository.findById(booking.getPickupAddress().getAddressId()).orElseThrow(
				() -> new ServiceException(ErrorCode.ADDRESS_NOT_FOUND, booking.getPickupAddress().getAddressId()));
		Address dropAddress = addressRepository.findById(booking.getDropAddress().getAddressId()).orElseThrow(
				() -> new ServiceException(ErrorCode.ADDRESS_NOT_FOUND, booking.getDropAddress().getAddressId()));
		updateBooking.setCustomer(customer);
		updateBooking.setCar(car);
		updateBooking.setPickupAddress(pickUpAddress);
		updateBooking.setDropAddress(dropAddress);
		updateBooking.setBookingStatus(booking.isBookingStatus());
		updateBooking.setPickupDate(booking.getPickupDate());
		updateBooking.setDropDate(booking.getDropDate());
		bookingRepository.save(updateBooking);
	}

	@Override
	public void deleteBooking(Long id) {
		log.info(">> deleteBooking({})", id);
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.BOOKING_NOT_FOUND, id));
		Car car = carRepository.findById(booking.getCar().getCarId())
				.orElseThrow(() -> new ServiceException(ErrorCode.CAR_NOT_FOUND, booking.getCar().getCarId()));
		car.setActive(true);
		bookingRepository.delete(booking);
	}

	@Override
	public Booking getBookingById(Long id) {
		log.info(">> getBookingById({})", id);
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.BOOKING_NOT_FOUND, id));
		return booking;
	}

}
