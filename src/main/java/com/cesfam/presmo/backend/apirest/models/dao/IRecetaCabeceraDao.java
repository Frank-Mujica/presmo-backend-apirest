package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.Medico;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;

public interface IRecetaCabeceraDao  extends JpaRepository<RecetaCabecera, Long>{
	
	@Query("from Medico")
	public List<Medico> findAllMedicos(); 

	@Query("from Paciente")
	public List<Paciente> findAllPacientes(); 
	
}
