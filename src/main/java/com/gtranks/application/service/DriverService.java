package com.gtranks.application.service;

import java.util.List;

import com.gtranks.application.domain.Driver;

public interface DriverService {
	List<Driver> getAll();
	Driver getById(long driverId);
	Driver getByNick(String nick);
	Driver create(Driver driver);
	void delete(long driverId);
	void update(long driverId, String driverNick);
}
