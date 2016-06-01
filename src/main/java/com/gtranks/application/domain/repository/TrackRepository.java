package com.gtranks.application.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtranks.application.domain.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {
	Track findByNameIgnoreCase(String name);
}
