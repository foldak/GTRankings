package com.gtranks.application.service;

import java.util.List;
import java.util.Map;

import com.gtranks.application.domain.Driver;
import com.gtranks.application.domain.Position;

public interface PositionService {
	List<Position> getByDriverId(long id);
	List<Position> getByRaceId(long id);
	List<Position> getAll();
	List<Position> findByRace_IdIn(long champId);
	List<Map<Driver,Integer>> getChampResults(long champId);
	Position getById(long id);
	
	void createMany(Iterable<Position> positions);
	void create(Position position);
	void update(Position position);
	void delete(long id);
	
	void deleteByRaceId(long raceId);
}
