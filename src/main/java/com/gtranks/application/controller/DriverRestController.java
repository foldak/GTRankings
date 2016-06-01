package com.gtranks.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtranks.application.domain.Driver;
import com.gtranks.application.service.DriverService;

@RestController
@RequestMapping(value = "api/driver")
public class DriverRestController {
	@Autowired
	private DriverService driverService;

	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public List<Driver> getAll() {
		return driverService.getAll();
	}

	@RequestMapping(value = "/get/byNick/{nick}", method = RequestMethod.GET)
	public Driver getByNick(@PathVariable String nick) {
		return driverService.getByNick(nick);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Driver getById(@PathVariable("id") long id) {
		return driverService.getById(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Driver createDriver(@Valid @RequestBody Driver newDriver,BindingResult br) {
		if (newDriver.getNick().isEmpty())
			throw new IllegalArgumentException("Nick nie może być pusty");
		else
			return driverService.create(newDriver);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteDriver(@PathVariable("id") long driverId) {
		driverService.delete(driverId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateDriver(@Validated @RequestBody Driver driver) {
		if (driver.getNick().isEmpty())
			throw new IllegalArgumentException("Nick nie może być pusty");
		else
			driverService.update(driver.getId(), driver.getNick());
	}

}
