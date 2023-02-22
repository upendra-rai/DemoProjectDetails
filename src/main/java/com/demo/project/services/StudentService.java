package com.demo.project.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import com.demo.project.models.Company;
import com.demo.project.models.PagedResponseDto;
import com.demo.project.models.Student;
import com.demo.project.models.one.to.one.uni.dir.User;
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;

public interface StudentService {

	public PagedResponseDto<Student> getLists(Pageable pageable);

	public void saveData(Student student);

	public void deleteById(Long id);
}
