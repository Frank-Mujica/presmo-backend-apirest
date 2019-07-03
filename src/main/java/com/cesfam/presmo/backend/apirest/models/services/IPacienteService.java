package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.Carnet;
import com.cesfam.presmo.backend.apirest.models.entity.Comuna;
import com.cesfam.presmo.backend.apirest.models.entity.EstadoCivil;
import com.cesfam.presmo.backend.apirest.models.entity.Nacionalidad;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;
import com.cesfam.presmo.backend.apirest.models.entity.Prevision;
import com.cesfam.presmo.backend.apirest.models.entity.Region;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;

public interface IPacienteService {
	
	public List<Paciente> findAll();
	
	public Page<Paciente> findAll(Pageable pageable);
	
	public Paciente findById(Long id);
	
	public Paciente save(Paciente articulo);
	
	public void delete (Long id);
	
	public List<EstadoCivil> findAllEstados_civiles();
	
	public List<Sexo> findAllSexos();
	
	public List<Prevision> findAllPrevisiones();
	
	public List<Carnet> findAllCarnets();
	
	public List<Region> findAllRegiones();
	
	public List<Comuna> findAllComunas();
	
	public List<Nacionalidad> findAllNacionalidades();

}