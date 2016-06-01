package com.gtranks.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gtranks.application.domain.Driver;
import com.gtranks.application.domain.repository.DriverRepository;
import com.gtranks.application.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService{
	@Autowired
	private DriverRepository driverRepository;
	
	@Override
	public List<Driver> getAll(){
		return driverRepository.findAll(new Sort(Sort.Direction.ASC, "nick"));
	}

	@Override
	public Driver create(Driver driver) {
		return driverRepository.save(driver);
	}

	@Override
	public void delete(long driverId) {
		driverRepository.delete(driverId);
	}

	@Override
	public Driver getById(long driverId) {
		return driverRepository.getOne(driverId);
	}

	@Override
	public void update(long driverId, String driverNick) {
		Driver driver = driverRepository.getOne(driverId);
		driver.setNick(driverNick);
		driverRepository.save(driver);
	}

	@Override
	public Driver getByNick(String nick) {
		return driverRepository.findByNickIgnoreCase(nick);
	}
	
	
}
