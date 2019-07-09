package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IEntregadoDao;
import com.cesfam.presmo.backend.apirest.models.entity.Entregado;
import com.cesfam.presmo.backend.apirest.models.entity.Farmaceutico;
import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaDetalle;

@Service
public class EntregadoServiceImpl implements IEntregadoService {

	@Autowired
	private IEntregadoDao entregadoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Entregado> findAll() {
		// TODO Auto-generated method stub
		return (List<Entregado>) entregadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Entregado> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return entregadoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Entregado findById(Long id) {
		// TODO Auto-generated method stub
		return entregadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Entregado save(Entregado entregado) {
		// TODO Auto-generated method stub
		return entregadoDao.save(entregado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		entregadoDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Farmaceutico> findAllFarmaceuticos() {
		// TODO Auto-generated method stub
		return entregadoDao.findAllfarmaceuticos();
	}

	@Override
	@Transactional
	public List<Partida> findAllPartidas() {
		// TODO Auto-generated method stub
		return entregadoDao.findAllPartidas();
	}
	
	@Override
	@Transactional
	public List<RecetaDetalle> findAllReceta_Detalles() {
		// TODO Auto-generated method stub
		return entregadoDao.findAllReceta_Detalles();
	}

}
