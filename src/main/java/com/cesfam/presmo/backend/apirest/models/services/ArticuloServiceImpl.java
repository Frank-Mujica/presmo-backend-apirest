package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IArticuloDao;
import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.Fabricante;
import com.cesfam.presmo.backend.apirest.models.entity.Tipo;

@Service
public class ArticuloServiceImpl implements IArticuloService{

	@Autowired
	private IArticuloDao articuloDao;
	@Override
	@Transactional(readOnly=true)
	public List<Articulo> findAll() {
		// TODO Auto-generated method stub
		return (List<Articulo>) articuloDao.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public Page<Articulo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return articuloDao.findAll(pageable);
	}
	@Override
	@Transactional(readOnly=true)
	public Articulo findById(Long id) {
		// TODO Auto-generated method stub
		return articuloDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public Articulo save(Articulo articulo) {
		// TODO Auto-generated method stub
		return articuloDao.save(articulo);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		articuloDao.deleteById(id);
	}
	@Override
	public List<Tipo> findAllTipos() {
		// TODO Auto-generated method stub
		return articuloDao.findAllTipos();
	}
	@Override
	public List<Fabricante> findAllFabricantes() {
		// TODO Auto-generated method stub
		return articuloDao.findAllFabricantes();
	}

}
