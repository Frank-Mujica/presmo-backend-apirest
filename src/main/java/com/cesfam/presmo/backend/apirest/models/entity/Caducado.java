package com.cesfam.presmo.backend.apirest.models.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Caducados")
public class Caducado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "El campo no puede estar vacío")
	@Column(nullable = false)
	private int cantidad;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Column(name = "fecha_desechado", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date fechaDesechado;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min = 9, max = 12, message = "el tamaño debe estar entre 9 y 12 caracteres")
	@Column(name = "rut_receptor", nullable = false, unique = true)
	private String rutReceptor;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 4, max = 30, message = "el tamaño debe estar entre 4 y 30 caracteres")
	@Column(name = "nombre_receptor", length = 30, nullable = false)
	private String nombreReceptor;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 4, max = 30, message = "el tamaño debe estar entre 4 y 30 caracteres")
	@Column(name = "relacion_receptor", length = 30, nullable = false)
	private String relacionReceptor;

	@NotNull(message = "Se debe indicar el farmacéutico a cargo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "farmaceutico_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Farmaceutico farmaceutico;

	@NotNull(message = "Se debe la partida de la cual se caduca el artículo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "partida_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Partida partida;

	@NotNull(message = "Se debe la partida de la cual se caduca el artículo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receta_detalle_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private RecetaDetalle recetaDetalle;

	@NotNull(message = "Se debe el motivo por el que se caduca el artículo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motivo_caducado_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private MotivoCaducado motivoCaducado;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaDesechado() {
		return fechaDesechado;
	}

	public void setFechaDesechado(Date fechaDesechado) {
		this.fechaDesechado = fechaDesechado;
	}

	public String getRutReceptor() {
		return rutReceptor;
	}

	public void setRutReceptor(String rutReceptor) {
		this.rutReceptor = rutReceptor;
	}

	public String getNombreReceptor() {
		return nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

	public String getRelacionReceptor() {
		return relacionReceptor;
	}

	public void setRelacionReceptor(String relacionReceptor) {
		this.relacionReceptor = relacionReceptor;
	}

	public Farmaceutico getFarmaceutico() {
		return farmaceutico;
	}

	public void setFarmaceutico(Farmaceutico farmaceutico) {
		this.farmaceutico = farmaceutico;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public RecetaDetalle getRecetaDetalle() {
		return recetaDetalle;
	}

	public void setRecetaDetalle(RecetaDetalle recetaDetalle) {
		this.recetaDetalle = recetaDetalle;
	}

	public MotivoCaducado getMotivoCaducado() {
		return motivoCaducado;
	}

	public void setMotivoCaducado(MotivoCaducado motivoCaducado) {
		this.motivoCaducado = motivoCaducado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
