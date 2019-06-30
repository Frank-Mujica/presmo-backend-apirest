package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import com.cesfam.presmo.backend.apirest.models.entity.Prevision;

public interface IPrevisionService {

public List<Prevision> findAll();
	
	public Prevision findById(Long id);
	
}
