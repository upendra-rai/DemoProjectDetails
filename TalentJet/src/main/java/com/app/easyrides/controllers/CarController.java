package com.app.easyrides.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.easyrides.dtos.PagedResponseDto;
import com.app.easyrides.entities.Car;
import com.app.easyrides.services.CarService;
import com.app.easyrides.util.FileUploadHelper;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cars")
public class CarController {

	@Value("${uploadDir}")
	private String uploadFolder;
	
	@Autowired
	private FileUploadHelper fileUploadHelper;

	@Autowired
	private CarService carService;

	@GetMapping
	public ResponseEntity<PagedResponseDto<Car>> getCarList(@QuerydslPredicate(root = Car.class) Predicate predicate,
			Pageable pageable) {
		log.info(">> getCarList({}, {})", predicate, pageable);
		return ResponseEntity.ok(carService.getCarList(predicate, pageable));
	}

	@PostMapping
	public ResponseEntity<Void> createCar(@RequestBody Car car) {
		log.info(">> createCar({})", car);
		carService.createCar(car);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/save")
	public ResponseEntity<String> createCarWithFile(@RequestParam("carName") String carName,
			@RequestParam("description") String description, @RequestParam("carModelYear") String carModelYear,
			@RequestParam("carBrand") String carBrand, @RequestParam("color") String color,
			@RequestParam("capacity") String capacity, @RequestParam("file") MultipartFile file,
			@RequestParam("plateNumber") String plateNumber, @RequestParam("rate") double rate,
			@RequestParam("carOwner") long carOwner, @RequestParam("status") String status) {
		try {
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			}
			if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Only JPEG content type are allowed ");
			}
			boolean fileName = fileUploadHelper.uploadFile(file);
			carService.createCarWithFile(carName, description, carModelYear, carBrand, color, capacity, file, plateNumber,
					rate, carOwner, status);
			if (fileName) {
				return ResponseEntity.ok("File Upload Successfully uploaded");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable Long id) {
		log.info(">> getCarById({})", id);
		return ResponseEntity.ok(carService.getCarById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCar(@PathVariable Long id, @RequestBody Car car) {
		log.info(">> updateCar({}, {})", id, car);
		carService.updateCar(id, car);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
		log.info(">> deleteCar({})", id);
		carService.deleteCar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping(value = "/get", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody byte[] getFile() throws IOException {
		InputStream in = getClass()
				.getResourceAsStream("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg");
		return IOUtils.toByteArray(in);
	}

	@GetMapping("/image")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("jpg") boolean jpg) {
		MediaType contentType = jpg ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
		InputStream in = jpg ? getClass().getResourceAsStream("C:\\Users\\HP\\Downloads\re.jpg")
				: getClass().getResourceAsStream(
						"https://cdn.pixabay.com/photo/2017/02/09/21/08/wings-2053515_960_720.png");
		return ResponseEntity.ok().contentType(contentType).body(new InputStreamResource(in));
	}

}
