package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.TratamientosLargos;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;

public interface ITratamientosLargosDao extends JpaRepository<TratamientosLargos, Long>{

	@Query("from RecetaCabecera")
	public List<RecetaCabecera> findAllReceta_Cabeceras(); 
	
}