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

import com.gtranks.application.domain.Track;
import com.gtranks.application.service.TrackService;

@RestController
@RequestMapping("/api/track")
public class TrackRestController {

	@Autowired
	private TrackService trackService;

	@RequestMapping("/get/all")
	public List<Track> getAll() {
		return trackService.getAll();
	}

	@RequestMapping("/get/{trackId}")
	public Track getById(@PathVariable long trackId) {
		return trackService.getById(trackId);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void create(@Validated @RequestBody Track newTrack, BindingResult br){
		if(newTrack.getName().isEmpty())
			throw new IllegalArgumentException("Nazwa toru nie może być pusta");
		else
		trackService.create(newTrack);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@Validated @RequestBody Track editTrack, BindingResult br){
		if(editTrack.getName().isEmpty())
			throw new IllegalArgumentException("Nazwa toru nie może być pusta");
		else{
		Track trackToEdit = trackService.getById(editTrack.getId());
		trackToEdit.setName(editTrack.getName());
		trackService.update(trackToEdit);
		}
	}
	
	@RequestMapping(value = "/delete/{trackId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long trackId){
		trackService.delete(trackId);
	}

}
