package com.cesfam.presmo.backend.apirest.models.services;

import com.cesfam.presmo.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
}
