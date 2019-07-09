package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.Entregado;
import com.cesfam.presmo.backend.apirest.models.entity.Farmaceutico;
import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaDetalle;

public interface IEntregadoDao extends JpaRepository<Entregado, Long>{

	@Query("from Farmaceutico")
	public List<Farmaceutico> findAllfarmaceuticos(); 
	
	@Query("from Partida")
	public List<Partida> findAllPartidas(); 
	
	@Query("from RecetaDetalle")
	public List<RecetaDetalle> findAllReceta_Detalles(); 
	
}