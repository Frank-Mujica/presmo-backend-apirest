package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import com.cesfam.presmo.backend.apirest.models.entity.Sexo;

public interface ISexoService {

public List<Sexo> findAll();
	
	public Sexo findById(Long id);
	
}
