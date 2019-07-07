package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.entity.Articulo;

public interface IPartidaService {

	public List<Partida> findAll();
	
	public Page<Partida> findAll(Pageable pageable);
	
	public Partida findById(Long id);
	
	public Partida save(Partida partida);
	
	public void delete (Long id);
	
	public List<Articulo> findAllArticulos();
	
}
