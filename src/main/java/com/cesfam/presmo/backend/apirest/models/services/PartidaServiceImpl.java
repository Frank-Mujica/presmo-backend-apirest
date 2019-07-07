package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IPartidaDao;
import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.entity.Articulo;

@Service
public class PartidaServiceImpl implements IPartidaService {

	@Autowired
	private IPartidaDao partidaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Partida> findAll() {
		// TODO Auto-generated method stub
		return (List<Partida>) partidaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Partida> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return partidaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Partida findById(Long id) {
		// TODO Auto-generated method stub
		return partidaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Partida save(Partida partida) {
		// TODO Auto-generated method stub
		return partidaDao.save(partida);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		partidaDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Articulo> findAllArticulos() {
		// TODO Auto-generated method stub
		return partidaDao.findAllArticulos();
	}

}
