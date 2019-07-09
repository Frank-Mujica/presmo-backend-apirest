package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IPacienteDao;
import com.cesfam.presmo.backend.apirest.models.dao.IReservaDao;
import com.cesfam.presmo.backend.apirest.models.entity.Reserva;
import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaDao reservaDao;
	
	@Autowired
	private IPacienteDao pacienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Reserva> findAll() {
		// TODO Auto-generated method stub
		return (List<Reserva>) reservaDao.findAll();
	}

	@Override
	public Page<Reserva> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return reservaDao.findAll(pageable);
	}

	@Override
	public Reserva findById(Long id) {
		// TODO Auto-generated method stub
		return reservaDao.findById(id).orElse(null);
	}

	@Override
	public Reserva save(Reserva reserva) {
		// TODO Auto-generated method stub
		return reservaDao.save(reserva);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		reservaDao.deleteById(id);
	}

	@Override
	public List<Articulo> findAllArticulos() {
		// TODO Auto-generated method stub
		return reservaDao.findAllArticulos();
	}

	@Override
	public List<Paciente> findAllPacientes() {
		// TODO Auto-generated method stub
		return reservaDao.findAllPacientes();
	}

	@Override
	public Paciente findByRut(String rut) {
		// TODO Auto-generated method stub
		return pacienteDao.findByRut(rut);
	}

}
