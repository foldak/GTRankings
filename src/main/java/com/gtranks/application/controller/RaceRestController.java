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

import com.gtranks.application.domain.Race;
import com.gtranks.application.service.RaceService;

@RestController
@RequestMapping("/api/race")
public class RaceRestController {

	@Autowired
	private RaceService raceService;

	@RequestMapping(value = "get/all")
	public List<Race> getAll() {
		return raceService.getAll();
	}

	@RequestMapping(value = "/get/byChampId/{champId}")
	public List<Race> getByChampId(@PathVariable long champId) {
		return raceService.getByChampionshipId(champId);
	}

	@RequestMapping(value = "/get/byTrackId/{trackId}")
	public List<Race> getByTrackId(@PathVariable long trackId) {
		return raceService.getByTrackId(trackId);
	}

	@RequestMapping(value = "/get/byRaceId/{raceId}")
	public Race getByRaceId(@PathVariable long raceId) {
		return raceService.getOne(raceId);
	}
	@RequestMapping(value = "/getIds/byChampId/{champId}")
	public List<Long> getRaceIdsByChampId(@PathVariable long champId){
		return raceService.getRaceIdsByChampionshipId(champId);
	}
	@RequestMapping(value = "/getDriverId/byChampId/{champId}")
	public List<Long> getDriverByChampId(@PathVariable long champId){
		return raceService.getDriver_IdByChampionship_Id(champId);
	}

	@RequestMapping(value = "/get/lastThree")
	public List<Race> getLastThree() {
		return raceService.getLastThree();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void createRace(@Validated @RequestBody Race newRace, BindingResult br) {
		if (newRace.getChampionship() == null)
			throw new IllegalArgumentException("Wybierz mistrzostwa do tego wyścigu");
		if (newRace.getTrack() == null)
			throw new IllegalArgumentException("Wybierz tor do tego wyścigu");
		if (newRace.getRw() < 0 || newRace.getRw() > 100)
			throw new IllegalArgumentException("Rw wyścigu musi być z przedziału od 0 do 10.");
		if (newRace.getDate() == null || newRace.getDate().toString()
				.matches("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])") == false)
			throw new IllegalArgumentException("Data wyścigu jest podana w złym formacie");
		else
			raceService.create(newRace);
	}

	@RequestMapping(value = "/delete/{raceId}", method = RequestMethod.DELETE)
	public void deleteRace(@PathVariable long raceId) {
		raceService.delete(raceId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateRace(@Validated @RequestBody Race editRace, BindingResult br) {
		if (editRace.getChampionship() == null)
			throw new IllegalArgumentException("Wybierz mistrzostwa do tego wyścigu");
		if (editRace.getTrack() == null)
			throw new IllegalArgumentException("Wybierz tor do tego wyścigu");
		if (editRace.getRw() < 0 || editRace.getRw() > 100)
			throw new IllegalArgumentException("Rw wyścigu musi być z przedziału od 0 do 10.");
		if (editRace == null || editRace.getDate().toString()
				.matches("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])") == false)
			throw new IllegalArgumentException("Data wyścigu jest podana w złym formacie");
		else {
			Race raceToEdit = raceService.getOne(editRace.getId());
			raceToEdit.setChampionship(editRace.getChampionship());
			raceToEdit.setDate(editRace.getDate());
			raceToEdit.setInOldboyRank(editRace.getInOldboyRank());
			raceToEdit.setRw(editRace.getRw());
			raceToEdit.setTrack(editRace.getTrack());
			raceService.update(raceToEdit);
		}
	}
}
