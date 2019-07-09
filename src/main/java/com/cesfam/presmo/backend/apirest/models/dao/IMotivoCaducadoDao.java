package com.cesfam.presmo.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesfam.presmo.backend.apirest.models.entity.MotivoCaducado;

public interface IMotivoCaducadoDao extends JpaRepository<MotivoCaducado, Long>{
	
}