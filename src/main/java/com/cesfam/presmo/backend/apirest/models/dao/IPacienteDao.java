package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.Carnet;
import com.cesfam.presmo.backend.apirest.models.entity.Comuna;
import com.cesfam.presmo.backend.apirest.models.entity.EstadoCivil;
import com.cesfam.presmo.backend.apirest.models.entity.Nacionalidad;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;
import com.cesfam.presmo.backend.apirest.models.entity.Prevision;
import com.cesfam.presmo.backend.apirest.models.entity.Region;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;

public interface IPacienteDao extends JpaRepository<Paciente, Long>{

	@Query("from EstadoCivil")
	public List<EstadoCivil> findAllEstados_civiles(); 
	
	@Query("from Sexo")
	public List<Sexo> findAllSexos(); 
	
	@Query("from Prevision")
	public List<Prevision> findAllPrevisiones(); 
	
	@Query("from Carnet")
	public List<Carnet> findAllCarnets(); 
	
	@Query("from Region")
	public List<Region> findAllRegiones(); 
	
	@Query("from Comuna")
	public List<Comuna> findAllComunas(); 
	
	@Query("from Nacionalidad")
	public List<Nacionalidad> findAllNacionalidades(); 
	
}
