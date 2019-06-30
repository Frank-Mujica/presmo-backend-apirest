package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.Carnet;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;
import com.cesfam.presmo.backend.apirest.models.entity.Prevision;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;

public interface IPacienteDao extends JpaRepository<Paciente, Long>{

	@Query("from sexo")
	public List<Sexo> findAllSexos(); 
	
	@Query("from prevision")
	public List<Prevision> findAllPrevisiones(); 
	
	@Query("from sexo")
	public List<Carnet> findAllCarnets(); 
	
}
