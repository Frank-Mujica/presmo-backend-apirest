package com.cesfam.presmo.backend.apirest.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tratamientos_largos")
public class TratamientosLargos {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "El campo no puede estar vacío")
	@Column(name = "fecha_ultima_dotacion", nullable = false)
	@JsonFormat(pattern= "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date fechaUltimaDotacion;
	@NotNull(message = "El campo no puede estar vacío")
	@Column(name = "fecha_proxima_dotacion", nullable = false)
	@JsonFormat(pattern= "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date fechaProximaDotacion;
	@NotNull(message = "El campo no puede estar vacío")
	@Column(name = "evaluacion_salud", nullable = false)
	private boolean evaluacionSalud;
	
	@NotNull(message = "Se deben indicar los artículos recetados")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receta_cabecera_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private RecetaCabecera recetaCabecera;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaUltimaDotacion() {
		return fechaUltimaDotacion;
	}

	public void setFechaUltimaDotacion(Date fechaUltimaDotacion) {
		this.fechaUltimaDotacion = fechaUltimaDotacion;
	}

	public Date getFechaProximaDotacion() {
		return fechaProximaDotacion;
	}

	public void setFechaProximaDotacion(Date fechaProximaDotacion) {
		this.fechaProximaDotacion = fechaProximaDotacion;
	}

	public boolean isEvaluacionSalud() {
		return evaluacionSalud;
	}

	public void setEvaluacionSalud(boolean evaluacionSalud) {
		this.evaluacionSalud = evaluacionSalud;
	}

	public RecetaCabecera getRecetaCabecera() {
		return recetaCabecera;
	}

	public void setRecetaCabecera(RecetaCabecera recetaCabecera) {
		this.recetaCabecera = recetaCabecera;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

