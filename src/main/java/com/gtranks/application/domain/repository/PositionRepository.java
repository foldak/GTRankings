package com.gtranks.application.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gtranks.application.domain.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
	
	
	List<Position> getByDriver_Id(long id);
	
	List<Position> getByRace_Id(long id);
	@Transactional
	Long deleteByRace_id(long raceId);
	
	List<Position> findByRace_IdIn(List<Long> raceIds);
	
}
