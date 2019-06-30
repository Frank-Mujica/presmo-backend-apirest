package com.cesfam.presmo.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesfam.presmo.backend.apirest.models.entity.Prevision;

public interface IPrevisionDao extends JpaRepository<Prevision, Long> {

	public Prevision findById(int id);
	
}
