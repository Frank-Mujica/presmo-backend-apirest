package com.cesfam.presmo.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;

public interface IPacienteDao extends JpaRepository<Paciente, Long>{

}
