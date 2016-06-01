package com.gtranks.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtranks.application.domain.Championship;
import com.gtranks.application.service.ChampionshipService;

@RestController
@RequestMapping(value = "api/champ")
public class ChampionshipRestController {
	@Autowired
	private ChampionshipService championshipService;

	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public List<Championship> getAll() {
		return championshipService.getAll();
	}

	@RequestMapping(value = "/get/{champId}", method = RequestMethod.GET)
	public Championship getById(@PathVariable long champId) {
		return championshipService.getById(champId);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void createChamp(@Validated @RequestBody Championship newChamp, BindingResult br) {
		if (newChamp.getName().isEmpty())
			throw new IllegalArgumentException("Nazwa mistrzostw nie może być pusta");
		if (newChamp.getNumberOfRaces() < 1 || newChamp.getNumberOfRaces() > 99)
			throw new IllegalArgumentException("Liczba wyścigów musi być liczbą z przedziału od 1 do 99");
		else
			championshipService.create(newChamp);

	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@Validated @RequestBody Championship editChamp, BindingResult br) {
		if (editChamp.getName().isEmpty())
			throw new IllegalArgumentException("Nazwa mistrzostw nie może być pusta");
		if (editChamp.getNumberOfRaces() < 1 || editChamp.getNumberOfRaces() > 99)
			throw new IllegalArgumentException("Liczba wyścigów musi być liczbą z przedziału od 1 do 99");
		else {
			Championship champToEdit = championshipService.getById(editChamp.getId());
			champToEdit.setName(editChamp.getName());
			champToEdit.setNumberOfRaces(editChamp.getNumberOfRaces());
			championshipService.update(champToEdit);
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") long champId) {
		championshipService.delete(champId);
	}
}
