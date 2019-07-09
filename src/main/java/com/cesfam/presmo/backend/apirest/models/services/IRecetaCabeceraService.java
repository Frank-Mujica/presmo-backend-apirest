package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;
import com.cesfam.presmo.backend.apirest.models.entity.Medico;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;

public interface IRecetaCabeceraService {
	
	public List<RecetaCabecera> findAll();
	
	public Page<RecetaCabecera> findAll(Pageable pageable);
	
	public RecetaCabecera findById(Long id);
	
	public RecetaCabecera save(RecetaCabecera recetaCabecera);
	
	public void delete (Long id);
	
	public List<Medico> findAllMedicos();
	
	public List<Paciente> findAllPacientes();
	
}
