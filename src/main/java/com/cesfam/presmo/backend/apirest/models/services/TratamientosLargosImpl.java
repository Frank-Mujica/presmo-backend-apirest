package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.ITratamientosLargosDao;
import com.cesfam.presmo.backend.apirest.models.entity.TratamientosLargos;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;

@Service
public class TratamientosLargosImpl implements ITratamientosLargosService {

	@Autowired
	private ITratamientosLargosDao tratamientosLargosDao;

	@Override
	@Transactional(readOnly = true)
	public List<TratamientosLargos> findAll() {
		// TODO Auto-generated method stub
		return (List<TratamientosLargos>) tratamientosLargosDao.findAll();
	}

	@Override
	@Transactional
	public Page<TratamientosLargos> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return tratamientosLargosDao.findAll(pageable);
	}

	@Override
	@Transactional
	public TratamientosLargos findById(Long id) {
		// TODO Auto-generated method stub
		return tratamientosLargosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public TratamientosLargos save(TratamientosLargos tratamientosLargos) {
		// TODO Auto-generated method stub
		return tratamientosLargosDao.save(tratamientosLargos);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		tratamientosLargosDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<RecetaCabecera> findAllReceta_Cabeceras() {
		// TODO Auto-generated method stub
		return tratamientosLargosDao.findAllReceta_Cabeceras();
	}
	
}