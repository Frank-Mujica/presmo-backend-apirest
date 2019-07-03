package com.cesfam.presmo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cesfam.presmo.backend.apirest.models.entity.Farmaceutico;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;
import com.cesfam.presmo.backend.apirest.models.entity.Cargo;
import com.cesfam.presmo.backend.apirest.models.entity.Farmacia;
import com.cesfam.presmo.backend.apirest.models.entity.Usuario;

public interface IFarmaceuticoDao extends JpaRepository<Farmaceutico, Long>{

	@Query("from Sexo")
	public List<Sexo> findAllSexos(); 
	
	@Query("from Cargo")
	public List<Cargo> findAllCargos(); 
	
	@Query("from Farmacia")
	public List<Farmacia> findAllFarmacias(); 
	
	@Query("from Usuario")
	public List<Usuario> findAllUsuarios(); 
	
}
