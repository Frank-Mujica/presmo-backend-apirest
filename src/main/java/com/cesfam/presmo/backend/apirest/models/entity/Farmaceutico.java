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
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "farmaceuticos")
public class Farmaceutico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 4, max = 30, message = "el tamaño debe estar entre 4 y 30 caracteres")
	@Column(length = 30, nullable = false)
	private String nombre;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 4, max = 50, message = "el tamaño debe estar entre 4 y 50 caracteres")
	@Column(name = "apellido_paterno", length = 50, nullable = false)
	private String apellidoPaterno;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 4, max = 50, message = "el tamaño debe estar entre 4 y 50 caracteres")
	@Column(name = " apellido_materno",length = 50, nullable = false)
	private String apellidoMaterno;
	@NotEmpty(message = "no puede estar vacío")
	@Column(unique = true, nullable = false)
	private String rut;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Column(name = "fecha_nacimiento", nullable = false)
	@JsonFormat(pattern= "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Pattern(regexp="^[0-9]+$", message = "El número de celular debe contener de 8 a 9 dígitos númericos")
	@Size(min = 8, max = 9, message = "El número de celular debe contener de 8 a 9 caracteres númericos")
	@Column(name = "numero_celular", nullable = false)
	private String numeroCelular;
	@Pattern(regexp="^[0-9]+$")
	@Size(min = 8, max = 9, message = "El número de teléfono debe contener de 8 a 9 caracteres númericos")
	@Column(name = "telefono_fijo", nullable = true)
	private String telefonoFijo;
	@Column(name = "foto_farmaceutico", nullable = true)
	private String foto;

	@NotNull(message = "Se debe indicar el sexo del farmacéutico")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sexo_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Sexo sexo;

	@NotNull(message = "Se debe indicar el cargo del farmacéutico")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cargo_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Cargo cargo;
	
	@NotNull(message = "Se debe indicar la farmacia del farmacéutico")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "farmacia_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Farmacia farmacia;

	@NotNull(message = "Se debe indicar un usuario para el farmacéutico")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}	

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

