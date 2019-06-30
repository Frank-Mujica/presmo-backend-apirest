package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IPrevisionDao;
import com.cesfam.presmo.backend.apirest.models.dao.ISexoDao;
import com.cesfam.presmo.backend.apirest.models.entity.Prevision;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;

public class PrevisionServiceImpl implements IPrevisionService {

	@Autowired
	private IPrevisionDao previsionDao;
	@Override
	@Transactional(readOnly=true)
	public Prevision findById(Long id) {
		// TODO Auto-generated method stub
		return previsionDao.findById(id).orElse(null);
	}
	
	@Override
	public List<Prevision> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
