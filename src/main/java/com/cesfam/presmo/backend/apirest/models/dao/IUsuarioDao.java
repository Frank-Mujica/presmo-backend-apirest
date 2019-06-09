package com.cesfam.presmo.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.cesfam.presmo.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);

}
