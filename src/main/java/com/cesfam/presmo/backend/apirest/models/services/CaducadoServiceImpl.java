package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.ICaducadoDao;
import com.cesfam.presmo.backend.apirest.models.entity.Caducado;
import com.cesfam.presmo.backend.apirest.models.entity.Farmaceutico;
import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaDetalle;
import com.cesfam.presmo.backend.apirest.models.entity.MotivoCaducado;


@Service
public class CaducadoServiceImpl implements ICaducadoService {

	@Autowired
	private ICaducadoDao caducadoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Caducado> findAll() {
		// TODO Auto-generated method stub
		return (List<Caducado>) caducadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Caducado> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return caducadoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Caducado findById(Long id) {
		// TODO Auto-generated method stub
		return caducadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Caducado save(Caducado caducado) {
		// TODO Auto-generated method stub
		return caducadoDao.save(caducado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		caducadoDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Farmaceutico> findAllFarmaceuticos() {
		// TODO Auto-generated method stub
		return caducadoDao.findAllfarmaceuticos();
	}

	@Override
	@Transactional
	public List<Partida> findAllPartidas() {
		// TODO Auto-generated method stub
		return caducadoDao.findAllPartidas();
	}
	
	@Override
	@Transactional
	public List<RecetaDetalle> findAllReceta_Detalles() {
		// TODO Auto-generated method stub
		return caducadoDao.findAllReceta_Detalles();
	}

	@Override
	public List<MotivoCaducado> findAllMotivo_Caducados() {
		// TODO Auto-generated method stub
		return caducadoDao.findAllMotivo_Caducados();
	}

}
