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
import com.app.easyrides.entities.CarOwner;
import com.app.easyrides.services.CarOwnerService;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/carOwners")
public class CarOwnerController {

	@Autowired
	private CarOwnerService carOwnerService;

	@GetMapping
	public ResponseEntity<PagedResponseDto<CarOwner>> getCarOwnerList(
			@QuerydslPredicate(root = CarOwner.class) Predicate predicate, Pageable pageable) {
		log.info(">> getCarOwnerList({}, {})", predicate, pageable);
		return ResponseEntity.ok(carOwnerService.getCarOwnerList(predicate, pageable));
	}
	
	@PostMapping
	public ResponseEntity<Void> createCarOwner(@RequestBody CarOwner carOwner) {
		log.info(">> createCarOwner({})", carOwner);
		carOwnerService.createCarOwner(carOwner);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarOwner> getCarOwnerById(@PathVariable Long id) {
		log.info(">> getCarOwnerById({})", id);
		return ResponseEntity.ok(carOwnerService.getCarOwnerById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCarOwner(@PathVariable Long id, @RequestBody CarOwner carOwner) {
		log.info(">> updateCarOwner({}, {})", id,carOwner);
		carOwnerService.updateCarOwner(id, carOwner);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCarOwner(@PathVariable Long id) {
		log.info(">> deleteCarOwner({})", id);
		carOwnerService.deleteCarOwner(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
