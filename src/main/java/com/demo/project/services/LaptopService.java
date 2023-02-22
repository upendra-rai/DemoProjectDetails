package com.demo.project.services;

import java.util.List;

import com.demo.project.models.Company;
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;

public interface LaptopService {
	
	public List<Laptop>getLists();
	public List<Color>getColorList();
	public void saveData(Laptop laptop);
	public Laptop getById(Long id);
	public void updateData(Long id,Laptop laptop);
	public void deleteById(Long id);
}
