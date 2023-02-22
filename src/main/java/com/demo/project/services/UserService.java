package com.demo.project.services;

import java.util.List;

import com.demo.project.models.Company;
import com.demo.project.models.one.to.one.uni.dir.User;
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;

public interface UserService {
	
	public List<User>getLists();
	
	public void saveData(User laptop);
	
	public void deleteById(Long id);
}
