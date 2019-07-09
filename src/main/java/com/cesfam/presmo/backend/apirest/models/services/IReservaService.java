package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesfam.presmo.backend.apirest.models.entity.Reserva;
import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;

public interface IReservaService {
	
	public List<Reserva> findAll();
	
	public Page<Reserva> findAll(Pageable pageable);
	
	public Reserva findById(Long id);
	
	public Reserva save(Reserva reserva);
	
	public void delete (Long id);
	
	public List<Articulo> findAllArticulos();
	
	public List<Paciente> findAllPacientes();
	
}