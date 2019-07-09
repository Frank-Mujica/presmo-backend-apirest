package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.RecetaDetalle;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;
import com.cesfam.presmo.backend.apirest.models.entity.Articulo;

public interface IRecetaDetalleService {
	
	public List<RecetaDetalle> findAll();
	
	public Page<RecetaDetalle> findAll(Pageable pageable);
	
	public RecetaDetalle findById(Long id);
	
	public RecetaDetalle save(RecetaDetalle recetaCabecera);
	
	public void delete (Long id);
	
	public List<RecetaCabecera> findAllReceta_cabeceras();
	
	public List<Articulo> findAllArticulos();
	
}