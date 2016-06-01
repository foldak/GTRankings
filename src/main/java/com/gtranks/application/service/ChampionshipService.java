package com.gtranks.application.service;

import java.util.List;

import com.gtranks.application.domain.Championship;

public interface ChampionshipService {
	List<Championship> getAll();
	Championship getById(long champId);
	Championship getByName(String champName);
	
	void create(Championship champ);
	void delete(long id);
	void update(Championship champ);
}
