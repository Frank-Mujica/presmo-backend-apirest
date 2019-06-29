package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IPacienteDao;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;

@Service
public class PacienteServiceImpl implements IPacienteService{

	@Autowired
	private IPacienteDao pacienteDao;
	@Override
	@Transactional(readOnly=true)
	public List<Paciente> findAll() {
		// TODO Auto-generated method stub
		return (List<Paciente>) pacienteDao.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public Page<Paciente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return pacienteDao.findAll(pageable);
	}
	@Override
	@Transactional(readOnly=true)
	public Paciente findById(Long id) {
		// TODO Auto-generated method stub
		return pacienteDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public Paciente save(Paciente articulo) {
		// TODO Auto-generated method stub
		return pacienteDao.save(articulo);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		pacienteDao.deleteById(id);
	}

}
