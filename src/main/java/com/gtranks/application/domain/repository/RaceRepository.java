package com.gtranks.application.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtranks.application.domain.Race;

public interface RaceRepository extends JpaRepository<Race, Long> {
	List<Race> getByChampionship_Id(long id);
	List<Race> getByTrack_Id(long id);
	List<Long> findDriver_IdByChampionship_Id(long id);
	}
