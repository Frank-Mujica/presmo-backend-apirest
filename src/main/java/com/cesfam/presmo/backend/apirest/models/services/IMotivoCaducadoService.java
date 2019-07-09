package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.MotivoCaducado;

public interface IMotivoCaducadoService {
	
	public List<MotivoCaducado> findAll();
	
	public Page<MotivoCaducado> findAll(Pageable pageable);
	
	public MotivoCaducado findById(Long id);
	
	public MotivoCaducado save(MotivoCaducado motivoCaducado);
	
	public void delete (Long id);
	
}
