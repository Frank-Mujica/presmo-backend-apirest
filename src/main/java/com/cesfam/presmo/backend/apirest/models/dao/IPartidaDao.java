package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.Partida;

public interface IPartidaDao extends JpaRepository<Partida, Long>{

	@Query("from Articulo")
	public List<Articulo> findAllArticulos(); 
}
