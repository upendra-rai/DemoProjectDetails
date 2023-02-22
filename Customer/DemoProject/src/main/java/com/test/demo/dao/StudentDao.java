package com.test.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.entities.Student;

public interface StudentDao extends JpaRepository<Student, Long> {

}
