package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.Entregado;
import com.cesfam.presmo.backend.apirest.models.entity.Farmaceutico;
import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaDetalle;

public interface IEntregadoService {
	
	public List<Entregado> findAll();
	
	public Page<Entregado> findAll(Pageable pageable);
	
	public Entregado findById(Long id);
	
	public Entregado save(Entregado entregado);
	
	public void delete (Long id);
	
	public List<Farmaceutico> findAllFarmaceuticos();
	
	public List<Partida> findAllPartidas();
	
	public List<RecetaDetalle> findAllReceta_Detalles();
	
}
