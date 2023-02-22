package com.app.easyrides.services;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Address;
import com.app.easyrides.entities.CarOwner;
import com.app.easyrides.exception.ErrorCode;
import com.app.easyrides.exception.ServiceException;
import com.app.easyrides.repositories.AddressRepository;
import com.app.easyrides.repositories.CarOwnerRepository;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarOwnerServiceImpl implements CarOwnerService {

	@Autowired
	private CarOwnerRepository carOwnerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	@Transactional
	public PagedResponseDto<CarOwner> getCarOwnerList(Predicate predicate, Pageable pageable) {
		  log.info(">> getCarOwnerList({}, {})", predicate, pageable);
		Page<CarOwner> carOwnerPage = carOwnerRepository.findAll(predicate, pageable);
		return PagedResponseDto.<CarOwner>builder()
				.list(carOwnerPage.stream().map(carOwner -> carOwner).collect(Collectors.toList()))
				.page(carOwnerPage.getNumber()).size(carOwnerPage.getSize())
				.totalElements(carOwnerPage.getTotalElements()).build();
	}

	@Override
	public void createCarOwner(CarOwner carOwner) {
		 log.info(">> createCarOwner({})", carOwner);
		Address address = addressRepository.save(carOwner.getAddress());
		carOwner.setAddress(address);
		carOwnerRepository.save(carOwner);
	}

	@Override
	public CarOwner getCarOwnerById(Long id) {
		log.info(">> getCarOwnerById({})", id);
		CarOwner carOwner = carOwnerRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.CAR_OWNER_NOT_FOUND, id));
		return carOwner;
	}

	@Override
	public void updateCarOwner(Long id, CarOwner carOwner) {
		log.info(">> updateCarOwner({}, {})", id,carOwner);
		CarOwner updateCarOwner = carOwnerRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.CAR_OWNER_NOT_FOUND, id));
		Address address=addressRepository.findById(carOwner.getAddress().getAddressId())
				.orElseThrow(() -> new ServiceException(ErrorCode.ADDRESS_NOT_FOUND, carOwner.getAddress().getAddressId()));
		address.setLine1(carOwner.getAddress().getLine1());
		address.setLine2(carOwner.getAddress().getLine2());
		address.setLine3(carOwner.getAddress().getLine3());
		address.setCity(carOwner.getAddress().getCity());
		address.setState(carOwner.getAddress().getState());
		address.setZipCode(carOwner.getAddress().getZipCode());
		updateCarOwner.setOwnerName(carOwner.getOwnerName());
		updateCarOwner.setContactNumber(carOwner.getContactNumber());
		addressRepository.save(address);
		carOwnerRepository.save(updateCarOwner);
	}

	@Override
	public void deleteCarOwner(Long id) {
		log.info(">> deleteCarOwner({})", id);
		CarOwner updateCarOwner = carOwnerRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.CAR_OWNER_NOT_FOUND, id));
		carOwnerRepository.delete(updateCarOwner);
	}

}
