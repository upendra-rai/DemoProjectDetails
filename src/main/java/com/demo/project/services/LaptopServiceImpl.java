package com.demo.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.project.dao.ColorDao;
import com.demo.project.dao.CompanyDao;
import com.demo.project.dao.LaptopDao;
import com.demo.project.models.Company;
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;

@Service
public class LaptopServiceImpl implements LaptopService {

	@Autowired
	private LaptopDao laptopDao;

	@Autowired
	private ColorDao colorDao;

	@Override
	public List<Laptop> getLists() {
		return laptopDao.findAllData();
	}
	
	@Override
	public List<Color> getColorList() {
		return colorDao.findAll();
	}

	@Override
	@Transactional
	public void saveData(Laptop laptop) {
		Color color=colorDao.save(laptop.getColor());
		laptop.setColor(color);
		laptopDao.save(laptop);
		
	}

	@Override
	public Laptop getById(Long id) {
		Laptop laptop = laptopDao.findById(id).orElseThrow(() -> new RuntimeException(" Id not Found"));
		return laptop;
	}

	@Override
	@Transactional
	public void updateData(Long id, Laptop laptop) {
		Laptop saveLaptop = laptopDao.findById(id).orElseThrow(() -> new RuntimeException(" Id not found"));
		Color saveColor = colorDao.findById(laptop.getId())
				.orElseThrow(() -> new RuntimeException(" Id not found"));
		saveColor.setColor(laptop.getColor().getColor());
		saveLaptop.setName(laptop.getName());
		saveLaptop.setBrand(laptop.getBrand());
		saveLaptop.setPrice(laptop.getPrice());
		laptopDao.save(saveLaptop);
		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Laptop laptop = laptopDao.findById(id).orElseThrow(() -> new RuntimeException(" Id not Found"));
		laptopDao.delete(laptop);
	}

}
