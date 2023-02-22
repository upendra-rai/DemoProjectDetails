package com.demo.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.project.models.Company;
import com.demo.project.one.to.many.bi.dir.models.Laptop;
@Repository
public interface LaptopDao extends JpaRepository<Laptop, Long> {
	
	@Query("select l from Laptop l where l.isActive = true order by l.price desc")
	 List<Laptop> findAllData();

}
