package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.TratamientosLargos;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;


public interface ITratamientosLargosService {
	
	public List<TratamientosLargos> findAll();
	
	public Page<TratamientosLargos> findAll(Pageable pageable);
	
	public TratamientosLargos findById(Long id);
	
	public TratamientosLargos save(TratamientosLargos tratamientosLargos);
	
	public void delete (Long id);
	
	public List<RecetaCabecera> findAllReceta_Cabeceras();
	
}