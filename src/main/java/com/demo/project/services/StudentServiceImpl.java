package com.demo.project.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.project.dao.AddressDao;
import com.demo.project.dao.ColorDao;
import com.demo.project.dao.CompanyDao;
import com.demo.project.dao.LaptopDao;
import com.demo.project.dao.StudentDao;
import com.demo.project.dao.UserDao;
import com.demo.project.models.Company;
import com.demo.project.models.PagedResponseDto;
import com.demo.project.models.Student;
import com.demo.project.models.one.to.one.uni.dir.Address;
import com.demo.project.models.one.to.one.uni.dir.User;
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public PagedResponseDto<Student> getLists(Pageable pageable) {
		 Page<Student>animalPage=studentDao.findAll(pageable);
		 var pageDto=new PagedResponseDto<Student>();
		 pageDto.setPage(animalPage.getNumber());
		 pageDto.setPageSize(animalPage.getSize());
		 pageDto.setTotalCount(animalPage.getTotalElements());
		 pageDto.setList(animalPage.getContent().stream().collect(Collectors.toList()));
			return pageDto;
	}

	@Override
	@Transactional
	public void saveData(Student student) {
		studentDao.save(student);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Student student = studentDao.findById(id).orElseThrow(() -> new RuntimeException(" Id not Found"));
		studentDao.delete(student);
	}

}
