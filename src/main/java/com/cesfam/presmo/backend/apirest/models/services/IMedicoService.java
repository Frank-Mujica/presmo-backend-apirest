package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.Medico;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;
import com.cesfam.presmo.backend.apirest.models.entity.Especialidad;
import com.cesfam.presmo.backend.apirest.models.entity.Usuario;

public interface IMedicoService {
	
	public List<Medico> findAll();
	
	public Page<Medico> findAll(Pageable pageable);
	
	public Medico findById(Long id);
	
	public Medico save(Medico medico);
	
	public void delete (Long id);
	
	public List<Sexo> findAllSexos();
	
	public List<Especialidad> findAllEspecialidades();
	
	public List<Usuario> findAllUsuarios();
	
}
