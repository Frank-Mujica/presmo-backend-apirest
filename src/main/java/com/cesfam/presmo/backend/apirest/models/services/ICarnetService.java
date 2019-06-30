package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import com.cesfam.presmo.backend.apirest.models.entity.Carnet;

public interface ICarnetService {

public List<Carnet> findAll();
	
	public Carnet findById(Long id);
	
}
