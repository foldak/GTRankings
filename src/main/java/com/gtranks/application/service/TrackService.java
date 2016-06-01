package com.gtranks.application.service;

import java.util.List;

import com.gtranks.application.domain.Track;

public interface TrackService {
	Track getById(long id);
	List<Track> getAll();
	
	void create(Track track);
	void update(Track id);
	void delete(long id);
}
