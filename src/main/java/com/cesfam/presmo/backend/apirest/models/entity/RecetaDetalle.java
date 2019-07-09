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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author fmuji
 *
 */
@Entity
@Table(name = "receta_detalle")
public class RecetaDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "estado_detalle", nullable = false)
	private boolean estadoDetalle;
	@NotNull(message = "El campo no puede estar vacío")
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	@NotNull(message = "El campo no puede estar vacío")
	@Column(nullable = false)
	private int dias;
	@NotNull(message = "El campo no puede estar vacío")
	@Column(nullable = false)
	private int intervalos;
	@Column(nullable = false)
	private boolean permanente;

	@NotNull(message = "La receta debe contener una cabecera")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receta_cabecera_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private RecetaCabecera recetaCabecera;

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

	public boolean isEstadoDetalle() {
		return estadoDetalle;
	}

	public void setEstadoDetalle(boolean estadoDetalle) {
		this.estadoDetalle = estadoDetalle;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public int getIntervalos() {
		return intervalos;
	}

	public void setIntervalos(int intervalos) {
		this.intervalos = intervalos;
	}

	public boolean isPermanente() {
		return permanente;
	}

	public void setPermanente(boolean permanente) {
		this.permanente = permanente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RecetaCabecera getRecetaCabecera() {
		return recetaCabecera;
	}

	public void setRecetaCabecera(RecetaCabecera recetaCabecera) {
		this.recetaCabecera = recetaCabecera;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

}
