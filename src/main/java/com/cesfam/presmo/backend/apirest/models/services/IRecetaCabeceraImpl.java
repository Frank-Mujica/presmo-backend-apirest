package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IRecetaCabeceraDao;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;
import com.cesfam.presmo.backend.apirest.models.entity.Medico;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;

@Service
public class IRecetaCabeceraImpl implements IRecetaCabeceraService {

	@Autowired
	private IRecetaCabeceraDao recetaCabeceraDao;

	@Override
	@Transactional(readOnly = true)
	public List<RecetaCabecera> findAll() {
		// TODO Auto-generated method stub
		return (List<RecetaCabecera>) recetaCabeceraDao.findAll();
	}

	@Override
	public Page<RecetaCabecera> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return recetaCabeceraDao.findAll(pageable);
	}

	@Override
	public RecetaCabecera findById(Long id) {
		// TODO Auto-generated method stub
		return recetaCabeceraDao.findById(id).orElse(null);
	}

	@Override
	public RecetaCabecera save(RecetaCabecera recetaCabecera) {
		// TODO Auto-generated method stub
		return recetaCabeceraDao.save(recetaCabecera);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		recetaCabeceraDao.deleteById(id);
	}

	@Override
	public List<Medico> findAllMedicos() {
		// TODO Auto-generated method stub
		return recetaCabeceraDao.findAllMedicos();
	}

	@Override
	public List<Paciente> findAllPacientes() {
		// TODO Auto-generated method stub
		return recetaCabeceraDao.findAllPacientes();
	}

}