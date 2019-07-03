package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.Farmaceutico;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;
import com.cesfam.presmo.backend.apirest.models.entity.Cargo;
import com.cesfam.presmo.backend.apirest.models.entity.Farmacia;
import com.cesfam.presmo.backend.apirest.models.entity.Usuario;

public interface IFarmaceuticoService {
	
	public List<Farmaceutico> findAll();
	
	public Page<Farmaceutico> findAll(Pageable pageable);
	
	public Farmaceutico findById(Long id);
	
	public Farmaceutico save(Farmaceutico farmaceutico);
	
	public void delete (Long id);
	
	public List<Sexo> findAllSexos();
	
	public List<Cargo> findAllCargos();
	
	public List<Farmacia> findAllFarmacias();
	
	public List<Usuario> findAllUsuarios();
	
}
