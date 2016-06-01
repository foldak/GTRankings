package com.gtranks.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gtranks.application.domain.Race;
import com.gtranks.application.domain.repository.RaceRepository;
import com.gtranks.application.service.RaceService;

@Service
public class RaceServiceImpl implements RaceService {
	@Autowired
	private RaceRepository raceRepository;

	@Override
	public List<Race> getByChampionshipId(long id) {
		List<Race> res = raceRepository.getByChampionship_Id(id);
		res.sort((r1,r2)->r2.getDate().compareTo(r1.getDate()));
		return res;

	}

	@Override
	public Race getOne(long id) {
		return raceRepository.getOne(id);
	}

	@Override
	public List<Race> getByTrackId(long id) {
		return raceRepository.getByTrack_Id(id);
	}

	@Override
	public List<Race> getAll() {
		return raceRepository.findAll(new Sort(Sort.Direction.DESC, "date"));
	}

	@Override
	public void create(Race race) {
		raceRepository.save(race);
	}

	@Override
	public void delete(long id) {
		raceRepository.delete(id);
	}

	@Override
	public void update(Race race) {
		raceRepository.save(race);
	}

	@Override
	public List<Race> getLastThree() {
		return getAll().subList(0, 3) ;
	}

	@Override
	public List<Long> getRaceIdsByChampionshipId(long id) {
		
		
		List<Long> racesIds = new ArrayList<>(10);
		for(Race race : raceRepository.getByChampionship_Id(id)){
			racesIds.add(race.getId());
		}
		return racesIds;
	}

	@Override
	public List<Long> getDriver_IdByChampionship_Id(long id) {
		return raceRepository.findDriver_IdByChampionship_Id(id);
	}

}
