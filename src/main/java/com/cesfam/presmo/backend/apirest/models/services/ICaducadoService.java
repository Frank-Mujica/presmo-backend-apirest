package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.Caducado;
import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.entity.MotivoCaducado;

public interface ICaducadoService {
	
	public List<Caducado> findAll();
	
	public Page<Caducado> findAll(Pageable pageable);
	
	public Caducado findById(Long id);
	
	public Caducado save(Caducado caducado);
	
	public void delete (Long id);
	
	public List<Partida> findAllPartidas();
	
	public List<MotivoCaducado> findAllMotivo_Caducados();
	
}