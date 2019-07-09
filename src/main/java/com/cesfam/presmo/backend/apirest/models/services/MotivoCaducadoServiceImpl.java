package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IMotivoCaducadoDao;
import com.cesfam.presmo.backend.apirest.models.entity.MotivoCaducado;


@Service
public class MotivoCaducadoServiceImpl implements IMotivoCaducadoService {

	@Autowired
	private IMotivoCaducadoDao motivoCaducadoDao;

	@Override
	@Transactional(readOnly = true)
	public List<MotivoCaducado> findAll() {
		// TODO Auto-generated method stub
		return (List<MotivoCaducado>) motivoCaducadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<MotivoCaducado> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return motivoCaducadoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public MotivoCaducado findById(Long id) {
		// TODO Auto-generated method stub
		return motivoCaducadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public MotivoCaducado save(MotivoCaducado motivoCaducado) {
		// TODO Auto-generated method stub
		return motivoCaducadoDao.save(motivoCaducado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		motivoCaducadoDao.deleteById(id);
	}
	
}
