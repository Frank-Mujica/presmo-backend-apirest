package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.Tipo;
import com.cesfam.presmo.backend.apirest.models.entity.Fabricante;

public interface IArticuloDao extends JpaRepository<Articulo, Long>{

	@Query("from tipo")
	public List<Tipo> findAllTipos(); 
	
	@Query("from fabricante")
	public List<Fabricante> findAllFabricantes(); 
}
