package com.gtranks.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtranks.application.domain.Track;
import com.gtranks.application.domain.repository.TrackRepository;
import com.gtranks.application.service.TrackService;

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TrackRepository trackRepository;

	@Override
	public Track getById(long trackId) {
		return trackRepository.findOne(trackId);
	}

	@Override
	public List<Track> getAll() {
		return trackRepository.findAll();
	}


	@Override
	public void create(Track track) {
		trackRepository.save(track);
	}

	@Override
	public void update(Track track) {
		trackRepository.save(track);
	}

	@Override
	public void delete(long id) {
		trackRepository.delete(id);
	}
}
