package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.Medico;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;
import com.cesfam.presmo.backend.apirest.models.entity.Especialidad;
import com.cesfam.presmo.backend.apirest.models.entity.Usuario;

public interface IMedicoDao extends JpaRepository<Medico, Long>{

	@Query("from Sexo")
	public List<Sexo> findAllSexos(); 
	
	@Query("from Especialidad")
	public List<Especialidad> findAllEspecialidades(); 
	
	@Query("from Usuario")
	public List<Usuario> findAllUsuarios(); 
	
}