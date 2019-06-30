package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.ISexoDao;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;

@Service
public class SexoServiceImpl implements ISexoService {

	@Autowired
	private ISexoDao sexoDao;
	@Override
	@Transactional(readOnly=true)
	public Sexo findById(Long id) {
		// TODO Auto-generated method stub
		return sexoDao.findById(id).orElse(null);
	}
	
	@Override
	public List<Sexo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
