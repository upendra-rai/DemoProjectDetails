package com.test.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.dao.StudentDao;
import com.test.demo.entities.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	// Get All Student List
	@Override
	public List<Student> getStudents() {
		return studentDao.findAll();
	}

	// Save New Student Details
	@Override
	public void createStudent(Student student) {
		studentDao.save(student);
	}

	// Get Student Details By Id
	@Override
	public Student getByStudentId(long id) {
		Student student = studentDao.findById(id).orElseThrow(() -> new RuntimeException("StudentId Not Found"));
		return student;
	}

	// Update Student By Id
	@Override
	@Transactional
	public void updateStudent(long id, Student student) {
		Student updateStudent = studentDao.findById(id).orElseThrow(() -> new RuntimeException("StudentId Not Found"));
		updateStudent.setName(student.getName());
		updateStudent.setRollNumber(student.getRollNumber());
		updateStudent.setCourse(student.getCourse());
		updateStudent.setEmail(student.getEmail());
		updateStudent.setAddress(student.getAddress());
	}

	// Delete Student By Id
	@Override
	public void deleteStudent(long id) {
		Student student = studentDao.findById(id).orElseThrow(() -> new RuntimeException("StudentId Not Found"));
		studentDao.delete(student);
	}

}
