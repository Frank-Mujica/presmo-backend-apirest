package com.cesfam.presmo.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesfam.presmo.backend.apirest.models.dao.IMedicoDao;
import com.cesfam.presmo.backend.apirest.models.entity.Medico;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;
import com.cesfam.presmo.backend.apirest.models.entity.Especialidad;
import com.cesfam.presmo.backend.apirest.models.entity.Usuario;

@Service
public class MedicoServiceImpl implements IMedicoService {

	@Autowired
	private IMedicoDao medicoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Medico> findAll() {
		// TODO Auto-generated method stub
		return (List<Medico>) medicoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Medico> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return medicoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Medico findById(Long id) {
		// TODO Auto-generated method stub
		return medicoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Medico save(Medico medico) {
		// TODO Auto-generated method stub
		return medicoDao.save(medico);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		medicoDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Sexo> findAllSexos() {
		// TODO Auto-generated method stub
		return medicoDao.findAllSexos();
	}

	@Override
	@Transactional
	public List<Especialidad> findAllEspecialidades() {
		// TODO Auto-generated method stub
		return medicoDao.findAllEspecialidades();
	}

	@Override
	@Transactional
	public List<Usuario> findAllUsuarios() {
		// TODO Auto-generated method stub
		return medicoDao.findAllUsuarios();
	}

}
