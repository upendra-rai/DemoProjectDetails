package com.app.easyrides.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	private Long userId;

	@Column(name = "full_name", length = 50, nullable = false)
	private String fullName;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "password", length = 255, nullable = false)
	private String password;
	
	@Column(name = "token", length = 200)
	private String token;
	
	private String role;
	
	private boolean accountStatus;

}
