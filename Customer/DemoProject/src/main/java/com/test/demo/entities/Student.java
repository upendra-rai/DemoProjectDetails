package com.test.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentId;

	@NotNull(message = "Name is mandatory")
	@Size(max = 100, min = 5)
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull(message = "RollNumber is mandatory")
	@Size(max = 100, min = 5)
	@Column(name = "rollNumber", nullable = false)
	private String rollNumber;

	@NotNull(message = "Course is mandatory")
	@Size(max = 100, min = 5)
	@Column(name = "course", nullable = false)
	private String course;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	public Student() {
		super();
	}

	public Student(long studentId, String name, String rollNumber, String course, String email, String address) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.rollNumber = rollNumber;
		this.course = course;
		this.email = email;
		this.address = address;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", rollNumber=" + rollNumber + ", course="
				+ course + ", email=" + email + ", address=" + address + "]";
	}

}
