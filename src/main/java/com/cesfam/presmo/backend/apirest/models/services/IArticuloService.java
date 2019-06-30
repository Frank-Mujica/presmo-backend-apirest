package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.Tipo;
import com.cesfam.presmo.backend.apirest.models.entity.Fabricante;

public interface IArticuloService {
	
	public List<Articulo> findAll();
	
	public Page<Articulo> findAll(Pageable pageable);
	
	public Articulo findById(Long id);
	
	public Articulo save(Articulo articulo);
	
	public void delete (Long id);
	
	public List<Tipo> findAllTipos();
	
	public List<Fabricante> findAllFabricantes();

}
