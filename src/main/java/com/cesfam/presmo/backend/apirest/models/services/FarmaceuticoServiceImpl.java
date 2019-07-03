package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IFarmaceuticoDao;
import com.cesfam.presmo.backend.apirest.models.entity.Farmaceutico;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;
import com.cesfam.presmo.backend.apirest.models.entity.Cargo;
import com.cesfam.presmo.backend.apirest.models.entity.Farmacia;
import com.cesfam.presmo.backend.apirest.models.entity.Usuario;

@Service
public class FarmaceuticoServiceImpl implements IFarmaceuticoService {

	@Autowired
	private IFarmaceuticoDao farmaceuticoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Farmaceutico> findAll() {
		// TODO Auto-generated method stub
		return (List<Farmaceutico>) farmaceuticoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Farmaceutico> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return farmaceuticoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Farmaceutico findById(Long id) {
		// TODO Auto-generated method stub
		return farmaceuticoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Farmaceutico save(Farmaceutico farmaceutico) {
		// TODO Auto-generated method stub
		return farmaceuticoDao.save(farmaceutico);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		farmaceuticoDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Sexo> findAllSexos() {
		// TODO Auto-generated method stub
		return farmaceuticoDao.findAllSexos();
	}

	@Override
	@Transactional
	public List<Cargo> findAllCargos() {
		// TODO Auto-generated method stub
		return farmaceuticoDao.findAllCargos();
	}
	
	@Override
	@Transactional
	public List<Farmacia> findAllFarmacias() {
		// TODO Auto-generated method stub
		return farmaceuticoDao.findAllFarmacias();
	}

	@Override
	public List<Usuario> findAllUsuarios() {
		// TODO Auto-generated method stub
		return farmaceuticoDao.findAllUsuarios();
	}

}