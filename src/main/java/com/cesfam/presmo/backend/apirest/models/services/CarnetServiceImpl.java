package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.ICarnetDao;
import com.cesfam.presmo.backend.apirest.models.entity.Carnet;

public class CarnetServiceImpl implements ICarnetService {

	@Autowired
	private ICarnetDao carnetDao;
	@Override
	@Transactional(readOnly=true)
	public Carnet findById(Long id) {
		// TODO Auto-generated method stub
		return carnetDao.findById(id).orElse(null);
	}
	
	@Override
	public List<Carnet> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
