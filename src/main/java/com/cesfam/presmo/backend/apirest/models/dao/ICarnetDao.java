package com.cesfam.presmo.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cesfam.presmo.backend.apirest.models.entity.Carnet;

public interface ICarnetDao extends JpaRepository<Carnet, Long>{

	public Carnet findById(int id);
	
}
