package com.gtranks.application.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtranks.application.domain.Championship;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {
	Championship getByNameIgnoreCase(String champName);
}
