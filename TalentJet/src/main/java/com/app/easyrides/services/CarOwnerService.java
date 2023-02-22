package com.app.easyrides.services;

import org.springframework.data.domain.Pageable;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.CarOwner;
import com.querydsl.core.types.Predicate;

public interface CarOwnerService {
	PagedResponseDto<CarOwner> getCarOwnerList(Predicate predicate, Pageable pageable);
	
	void createCarOwner(CarOwner carOwner);

	CarOwner getCarOwnerById(Long id);

	void updateCarOwner(Long id, CarOwner carOwner);

	void deleteCarOwner(Long id);

}
