package com.gtranks.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gtranks.application.domain.Driver;
import com.gtranks.application.domain.Position;
import com.gtranks.application.service.PositionService;

@RestController
@RequestMapping("api/position")
public class PositionRestController {

	@Autowired
	private PositionService positionService;

	@RequestMapping("/get/byDriverId/{driverId}")
	public List<Position> getByDriverId(@PathVariable long driverId) {
		return positionService.getByDriverId(driverId);
	}

	@RequestMapping("/get/all")
	public List<Position> getAll() {
		return positionService.getAll();
	}

	@RequestMapping("/get/byRaceId/{raceId}")
	public List<Position> getByRaceId(@PathVariable long raceId) {
		return positionService.getByRaceId(raceId);
	}

	@RequestMapping("/get/byChampId/{champId}")
	public List<Position> getByChampId(@PathVariable long champId) {
		return positionService.findByRace_IdIn(champId);
	}

	@RequestMapping("/get/resultsByChampId/{champId}")
	public Pair<List<Driver>, List<Integer>> getChampResults(@PathVariable long champId) throws JsonProcessingException {
		List<Map<Driver,Integer>> src = positionService.getChampResults(champId);
		List<Driver> drivers = new ArrayList<>(16);
		List<Integer> driverPoints = new ArrayList<>(16);
		for(Map<Driver,Integer> result : src){
			Stream<Driver> driverStr = result.keySet().stream();
			Driver driver = driverStr.findFirst().get();
			Stream<Integer> pointsStr = result.values().stream();
			Integer points = pointsStr.findFirst().get();
			drivers.add(driver);
			driverPoints.add(points);
		}
		
		
		return Pair.of(drivers, driverPoints);
	}

	@RequestMapping(value = "/create/many", method = RequestMethod.POST)
	public void createMany(@RequestBody Iterable<Position> positions, BindingResult br) {

		positionService.createMany(positions);
	}

	@RequestMapping(value = "/delete/{positionId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long positionId) {
		positionService.delete(positionId);
	}

	@RequestMapping(value = "/delete/byRaceId/{raceId}", method = RequestMethod.DELETE)
	public void deleteByRaceId(@PathVariable long raceId) {
		positionService.deleteByRaceId(raceId);
	}
}
