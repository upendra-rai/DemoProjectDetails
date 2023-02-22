package com.app.easyrides.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.easyrides.entities.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}