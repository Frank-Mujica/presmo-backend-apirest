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
@Table(name = "partidas")
public class Partida {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "El campo no puede estar vacío")
	@Column(name = "fecha_partida", nullable = false)
	@JsonFormat(pattern= "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date fechaPartida;
	@NotNull(message = "El campo no puede estar vacío")
	@Column(name = "cantidad_llegada", nullable = false)
	private int cantidadLlegada;
	@Column(name = "cantidad_restante", nullable = false)
	private int cantidadRestante;
	
	@NotNull(message = "Se deben indicar los artículos recetados")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articulo_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Articulo articulo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaPartida() {
		return fechaPartida;
	}

	public void setFechaPartida(Date fechaPartida) {
		this.fechaPartida = fechaPartida;
	}

	public int getCantidadLlegada() {
		return cantidadLlegada;
	}

	public void setCantidadLlegada(int cantidadLlegada) {
		this.cantidadLlegada = cantidadLlegada;
	}

	public int getCantidadRestante() {
		return cantidadRestante;
	}

	public void setCantidadRestante(int cantidadRestante) {
		this.cantidadRestante = cantidadRestante;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
