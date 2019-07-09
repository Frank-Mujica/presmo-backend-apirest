package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IRecetaDetalleDao;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaDetalle;
import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;

@Service
public class IRecetaDetalleImpl implements IRecetaDetalleService {

	@Autowired
	private IRecetaDetalleDao recetaDetalleDao;

	@Override
	@Transactional(readOnly = true)
	public List<RecetaDetalle> findAll() {
		// TODO Auto-generated method stub
		return (List<RecetaDetalle>) recetaDetalleDao.findAll();
	}

	@Override
	public Page<RecetaDetalle> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return recetaDetalleDao.findAll(pageable);
	}

	@Override
	public RecetaDetalle findById(Long id) {
		// TODO Auto-generated method stub
		return recetaDetalleDao.findById(id).orElse(null);
	}

	@Override
	public RecetaDetalle save(RecetaDetalle recetaDetalle) {
		// TODO Auto-generated method stub
		return recetaDetalleDao.save(recetaDetalle);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		recetaDetalleDao.deleteById(id);
	}

	@Override
	public List<Articulo> findAllArticulos() {
		// TODO Auto-generated method stub
		return recetaDetalleDao.findAllArticulos();
	}

	@Override
	public List<RecetaCabecera> findAllReceta_Cabeceras() {
		// TODO Auto-generated method stub
		return recetaDetalleDao.findAllReceta_Cabeceras();
	}

}
