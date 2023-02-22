package com.demo.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.models.Company;
import com.demo.project.one.to.many.bi.dir.models.Color;
@Repository
public interface ColorDao extends JpaRepository<Color, Long> {

}
