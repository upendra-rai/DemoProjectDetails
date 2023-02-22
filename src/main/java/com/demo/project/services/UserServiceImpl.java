package com.demo.project.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.project.dao.AddressDao;
import com.demo.project.dao.ColorDao;
import com.demo.project.dao.CompanyDao;
import com.demo.project.dao.LaptopDao;
import com.demo.project.dao.UserDao;
import com.demo.project.models.Company;
import com.demo.project.models.one.to.one.uni.dir.Address;
import com.demo.project.models.one.to.one.uni.dir.User;
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AddressDao addressDao;

	@Override
	public List<User> getLists() {
		return userDao.findAll();
	}

	@Override
	@Transactional
	public void saveData(User user) {
		User saveUser=userDao.save(user);
		user.getAddress().stream().forEach(add->{
			Address address= new Address();
			address.setUser(saveUser);
			address.setStreet(add.getStreet());
			addressDao.save(address);
		});
		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		User user = userDao.findById(id).orElseThrow(() -> new RuntimeException(" Id not Found"));
		userDao.delete(user);
	}

}
