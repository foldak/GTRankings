package com.gtranks.application.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtranks.application.domain.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	Driver findByNickIgnoreCase(String name);
	List<Driver> findByIdIn(List<Long> ids);
}
