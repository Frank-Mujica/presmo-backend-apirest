package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IPacienteDao;
import com.cesfam.presmo.backend.apirest.models.entity.Carnet;
import com.cesfam.presmo.backend.apirest.models.entity.Comuna;
import com.cesfam.presmo.backend.apirest.models.entity.EstadoCivil;
import com.cesfam.presmo.backend.apirest.models.entity.Nacionalidad;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;
import com.cesfam.presmo.backend.apirest.models.entity.Prevision;
import com.cesfam.presmo.backend.apirest.models.entity.Region;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;

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
	@Transactional(readOnly=true)
	public Paciente findByRut(String rut) {
		// TODO Auto-generated method stub
		return pacienteDao.findByRut(rut);
	}
	@Override
	@Transactional
	public Paciente save(Paciente paciente) {
		// TODO Auto-generated method stub
		return pacienteDao.save(paciente);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		pacienteDao.deleteById(id);
	}
	@Override
	@Transactional
	public List<EstadoCivil> findAllEstados_civiles() {
		// TODO Auto-generated method stub
		return pacienteDao.findAllEstados_civiles();
	}
	@Override
	@Transactional
	public List<Sexo> findAllSexos() {
		// TODO Auto-generated method stub
		return pacienteDao.findAllSexos();
	}
	@Override
	@Transactional
	public List<Prevision> findAllPrevisiones() {
		// TODO Auto-generated method stub
		return pacienteDao.findAllPrevisiones();
	}
	@Override
	@Transactional
	public List<Carnet> findAllCarnets() {
		// TODO Auto-generated method stub
		return pacienteDao.findAllCarnets();
	}
	@Override
	public List<Region> findAllRegiones() {
		// TODO Auto-generated method stub
		return pacienteDao.findAllRegiones();
	}
	@Override
	public List<Comuna> findAllComunas() {
		// TODO Auto-generated method stub
		return pacienteDao.findAllComunas();
	}
	@Override
	public List<Nacionalidad> findAllNacionalidades() {
		// TODO Auto-generated method stub
		return pacienteDao.findAllNacionalidades();
	}

}
