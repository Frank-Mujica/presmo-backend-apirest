package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.Caducado;
import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.entity.MotivoCaducado;

public interface ICaducadoDao extends JpaRepository<Caducado, Long>{

	@Query("from Partida")
	public List<Partida> findAllPartidas(); 
	
	@Query("from MotivoCaducado")
	public List<MotivoCaducado> findAllMotivo_Caducados(); 
	
}