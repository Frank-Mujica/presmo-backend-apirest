package com.cesfam.presmo.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "receta_cabeceras")
public class RecetaCabecera implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min = 4, max = 50, message = "el tamaño debe estar entre 4 y 50 caracteres")
	@Column(name = "estado_receta", nullable = false)
	private String estadoReceta;

	@NotNull(message = "Se debe indicar el médico que emite la receta")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Medico medico;

	@NotNull(message = "Se debe indicar el paciente al cual está ligada la receta")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Paciente paciente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstadoReceta() {
		return estadoReceta;
	}

	public void setEstadoReceta(String estadoReceta) {
		this.estadoReceta = estadoReceta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}