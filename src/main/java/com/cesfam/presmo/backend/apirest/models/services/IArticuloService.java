package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import com.cesfam.presmo.backend.apirest.models.entity.Articulo;

public interface IArticuloService {
	
	public List<Articulo> findAll();

}
