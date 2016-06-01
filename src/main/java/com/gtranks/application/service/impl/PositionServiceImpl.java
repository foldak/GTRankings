package com.gtranks.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtranks.application.domain.Driver;
import com.gtranks.application.domain.Position;
import com.gtranks.application.domain.repository.PositionRepository;
import com.gtranks.application.service.PositionService;
import com.gtranks.application.service.RaceService;
@Service
public class PositionServiceImpl implements PositionService {
	@Autowired 
	private PositionRepository positionRepository;
	
	@Autowired 
	private RaceService raceService;

	@Override
	public List<Position> getByDriverId(long driverId) {
		return positionRepository.getByDriver_Id(driverId);
	}

	@Override
	public List<Position> getByRaceId(long raceId) {
		return positionRepository.getByRace_Id(raceId);
	}

	@Override
	public void create(Position position) {
		positionRepository.save(position);
	}

	@Override
	public void update(Position position) {
		positionRepository.save(position);
	}

	@Override
	public void delete(long id) {
		positionRepository.delete(id);
		
	}

	@Override
	public Position getById(long id) {
		return positionRepository.getOne(id);
	}

	@Override
	public void createMany(Iterable<Position> positions) {
		positionRepository.save(positions);
	}
	
	@Override
	public void deleteByRaceId(long raceId) {
		positionRepository.deleteByRace_id(raceId);
	}

	@Override
	public List<Position> getAll() {
		return positionRepository.findAll();
	}

	@Override
	public List<Position> findByRace_IdIn(long champId) {
		List<Long> raceIds = raceService.getRaceIdsByChampionshipId(champId);
		return positionRepository.findByRace_IdIn(raceIds);
	}

	@Override
	public List<Map<Driver, Integer>> getChampResults(long champId) {
		
		List<Map<Driver,Integer>> res = new ArrayList<>(16);
		Set<Driver> drivers = getDriversByChampionshipId(champId);
		positionListByChampId = findByRace_IdIn(champId);
		for(Driver driver : drivers){
			res.add(getOneResultInChamp(driver));
		}
		
		res.sort((o1,o2)-> o2.entrySet().stream().findFirst().get().getValue().compareTo(o1.entrySet().stream().findFirst().get().getValue()));
		return res;
		
	}
	
	private Set<Driver> getDriversByChampionshipId(long champId){
		Set<Driver> drivers = new HashSet<>();
		for(Position position : findByRace_IdIn(champId)){
			drivers.add(position.getDriver());
		}
		return drivers;
	}
	private Map<Driver,Integer> getOneResultInChamp(Driver driver){
		int[] pointsArr = {0,25,18,15,12,10,8,6,4,2,1,0,0,0,0,0,0};
		int points = 0;
		Map<Driver,Integer> result = new HashMap<>();
		for(Position position : positionListByChampId){
			if(position.getDriver().equals(driver)){
				points += pointsArr[position.getPosition()];
				result.put(position.getDriver(),points);
			}
		}
		return result;
	}
	List<Position> positionListByChampId = null;
	
}
