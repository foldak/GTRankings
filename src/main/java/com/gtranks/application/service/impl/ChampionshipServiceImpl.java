package com.gtranks.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtranks.application.domain.Championship;
import com.gtranks.application.domain.repository.ChampionshipRepository;
import com.gtranks.application.service.ChampionshipService;

@Service
public class ChampionshipServiceImpl implements ChampionshipService {
	@Autowired
	private ChampionshipRepository championshipRepository;

	@Override
	public List<Championship> getAll() {
		return championshipRepository.findAll();
	}

	@Override
	public Championship getById(long champId) {
		return championshipRepository.findOne(champId);
	}

	@Override
	public Championship getByName(String champName) {
		return championshipRepository.getByNameIgnoreCase(champName);
	}

	@Override
	public void create(Championship champ) {
		championshipRepository.save(champ);
	}

	@Override
	public void delete(long id) {
		championshipRepository.delete(id);
	}

	@Override
	public void update(Championship champ) {
		championshipRepository.save(champ);
	}
	
	
}
