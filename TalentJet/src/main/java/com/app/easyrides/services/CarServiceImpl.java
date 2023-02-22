package com.app.easyrides.services;

import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Car;
import com.app.easyrides.entities.CarOwner;
import com.app.easyrides.entities.QCar;
import com.app.easyrides.entities.RentCar;
import com.app.easyrides.exception.ErrorCode;
import com.app.easyrides.exception.ServiceException;
import com.app.easyrides.repositories.CarOwnerRepository;
import com.app.easyrides.repositories.CarRepository;
import com.app.easyrides.repositories.RentCarRepository;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarOwnerRepository carOwnerRepository;
	
	@Autowired
	private RentCarRepository rentCarRepository;

	@Override
	@Transactional
	public PagedResponseDto<Car> getCarList(Predicate predicate, Pageable pageable) {
		log.info(">> getCarList({}, {})", predicate, pageable);
		predicate=QCar.car.isActive.eq(true);
		Page<Car> carPage = carRepository.findAll(predicate, pageable);
		return PagedResponseDto.<Car>builder()
				.list(carPage.stream().map(carOwner -> carOwner).collect(Collectors.toList()))
				.page(carPage.getNumber()).size(carPage.getSize())
				.totalElements(carPage.getTotalElements()).build();
	}
	
	
	@Override
	public void createCar(Car car) {
		log.info(">> createCar({})", car);
		CarOwner carOwner = carOwnerRepository.findById(car.getCarOwner().getCarOwnerId()).orElseThrow(
				() -> new ServiceException(ErrorCode.CAR_OWNER_NOT_FOUND, car.getCarOwner().getCarOwnerId()));
		RentCar rentCar= new RentCar();
		rentCar.setCar(car);
		rentCarRepository.save(rentCar);
		car.setCarOwner(carOwner);
		carRepository.save(car);
	}

	@Override
	public Car getCarById(Long id) {
		log.info(">> getCarById({})", id);
		Car car = carRepository.findByActiveId(id).orElseThrow(() -> new ServiceException(ErrorCode.CAR_OWNER_NOT_FOUND, id));
		return car;
	}

	@Override
	public void updateCar(Long id, Car car) {
		log.info(">> updateCar({}, {})", id, car);
		Car updateCar = carRepository.findById(id)
				.orElseThrow(() -> new ServiceException(ErrorCode.CAR_OWNER_NOT_FOUND, id));
		CarOwner carOwner = carOwnerRepository.findById(car.getCarOwner().getCarOwnerId()).orElseThrow(
				() -> new ServiceException(ErrorCode.CAR_OWNER_NOT_FOUND, car.getCarOwner().getCarOwnerId()));
		updateCar.setCarName(car.getCarName());
		updateCar.setDescription(car.getDescription());
		updateCar.setCarModelYear(car.getCarModelYear());
		updateCar.setCarBrand(car.getCarBrand());
		updateCar.setColor(car.getColor());
		updateCar.setCapacity(car.getCapacity());
		updateCar.setPlateNumber(car.getPlateNumber());
		updateCar.setRate(car.getRate());
		updateCar.setStatus(car.getStatus());
		updateCar.setCarOwner(carOwner);
		carRepository.save(updateCar);
	}

	@Override
	public void deleteCar(Long id) {
		log.info(">> deleteCar({}, {})", id);
		Car car = carRepository.findById(id).orElseThrow(() -> new ServiceException(ErrorCode.CAR_OWNER_NOT_FOUND, id));
		carRepository.delete(car);
	}

	@Override
	public void createCarWithFile(String carName, String description, String carModelYear, String carBrand,
			String color, String capacity, MultipartFile file, String plateNumber, double rate, long carOwner,
			String status) {
		CarOwner savecarOwner = carOwnerRepository.findById(carOwner)
				.orElseThrow(() -> new ServiceException(ErrorCode.CAR_OWNER_NOT_FOUND, carOwner));
		Car car = new Car();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.contains("..")) {
			System.out.println("not a a valid file");
		}
		try {
			car.setImagefile(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		car.setCarName(carName);
		car.setDescription(description);
		car.setCarModelYear(carModelYear);
		car.setCarBrand(carBrand);
		car.setColor(color);
		car.setCapacity(capacity);
		car.setPlateNumber(plateNumber);
		car.setRate(rate);
		car.setCarOwner(savecarOwner);
		car.setStatus(status);		
		carRepository.save(car);
	}

	@Override
	public void createCarWithFile(Car car) {
		CarOwner carOwner = carOwnerRepository.findById(car.getCarOwner().getCarOwnerId()).orElseThrow(
				() -> new ServiceException(ErrorCode.CAR_OWNER_NOT_FOUND, car.getCarOwner().getCarOwnerId()));
		car.setCarOwner(carOwner);
		car.setImagefile(car.getImagefile());
		carRepository.save(car);

	}

}
