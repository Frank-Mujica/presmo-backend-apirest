package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaDetalle;

public interface IRecetaDetalleDao  extends JpaRepository<RecetaDetalle, Long>{
	
	@Query("from Articulo")
	public List<Articulo> findAllArticulos(); 

	@Query("from RecetaCabecera")
	public List<RecetaCabecera> findAllReceta_Cabeceras(); 
	
}

