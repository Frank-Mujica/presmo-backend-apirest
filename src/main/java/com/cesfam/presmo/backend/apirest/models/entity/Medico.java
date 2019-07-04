package com.cesfam.presmo.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;*/
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "medicos")
public class Medico implements Serializable {

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
	@Column(name = "apellido_materno", length = 50, nullable = false)
	private String apellidoMaterno;
	@NotEmpty(message = "no puede estar vacío")
	@Column(unique = true, nullable = false)
	private String rut;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Column(name = "fecha_nacimiento", nullable = false)
	/*@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern="dd/MM/yyyy")*/
	private Date fechaNacimiento;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min = 8, max = 8, message = "el número de celular debe tener 8 digitos")
	@Column(name = "numero_celular", nullable = false)
	private int numeroCelular;
	@Size(min = 8, max = 8, message = "el número de teléfono debe tener 8 digitos")
	@Column(name = "telefono_fijo", nullable = true, columnDefinition="int default null")
	private int telefonoFijo;
	@Column(name = "foto_medico", nullable = true)
	private String foto;

	@NotNull(message = "Se debe indicar el sexo del médico")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "sexo_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Sexo sexo;

	@NotNull(message = "Se debe indicar la especialidad del médico")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "especialidad_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Especialidad especialidad;

	@NotNull(message = "Se debe indicar un usuario para el médico")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

	public int getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(int numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public int getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(int telefonoFijo) {
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

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
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
