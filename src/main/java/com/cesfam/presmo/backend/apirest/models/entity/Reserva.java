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
@Table(name = "reservas")
public class Reserva {

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
	@NotNull(message = "El campo no puede estar vacío")
	@Column(name = "fecha_reserva", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date fechaReserva;
	@NotNull(message = "El campo no puede estar vacío")
	@Column(nullable = false)
	private boolean retirara;

	@NotNull(message = "Se deben indicar los artículos reservados")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articulo_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Articulo articulo;

	@NotNull(message = "Se deben indicar el paciente que reservará")
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public boolean isRetirara() {
		return retirara;
	}

	public void setRetirara(boolean retirara) {
		this.retirara = retirara;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
