package com.demo.project.one.to.many.bi.dir.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "laptop")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Laptop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	private String name;

	private String brand;

	private Double price;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "color_id")
	private Color color;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private LaptopType laptopType;

	private Boolean isActive;

	public enum LaptopType {
		GAMING, STUDENT, COMMERCIAL;
	}

}
