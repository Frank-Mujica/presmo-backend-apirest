package com.cesfam.presmo.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.cesfam.presmo.backend.apirest.models.entity.Articulo;

public interface IArticuloDao extends JpaRepository<Articulo, Long>{

}
