package com.test.demo.services;

import java.util.List;

import com.test.demo.entities.Student;

public interface StudentService {

	List<Student> getStudents();

	void createStudent(Student student);

	Student getByStudentId(long id);

	void updateStudent(long id, Student student);

	void deleteStudent(long id);

}
