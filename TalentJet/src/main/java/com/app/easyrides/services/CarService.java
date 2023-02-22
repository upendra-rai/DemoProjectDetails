package com.app.easyrides.services;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Car;
import com.querydsl.core.types.Predicate;

public interface CarService {
	PagedResponseDto<Car> getCarList(Predicate predicate, Pageable pageable);

	void createCar(Car car);

	Car getCarById(Long id);

	void updateCar(Long id, Car car);

	void deleteCar(Long id);

	void createCarWithFile(String carName, String description, String carModelYear, String carBrand, String color,
			String capacity, MultipartFile file, String plateNumber, double rate, long carOwner, String status);

	void createCarWithFile( Car car);

}
