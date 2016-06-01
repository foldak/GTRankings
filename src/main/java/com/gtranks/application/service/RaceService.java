package com.gtranks.application.service;

import java.util.List;

import com.gtranks.application.domain.Race;

public interface RaceService {
	List<Race> getAll();
	List<Race> getByChampionshipId(long id);
	List<Race> getByTrackId(long id);
	List<Race> getLastThree();
	List<Long> getRaceIdsByChampionshipId(long id);
	List<Long> getDriver_IdByChampionship_Id(long id);
	Race getOne(long id);
	void create(Race race);
	void delete(long id);
	void update(Race race);
}
