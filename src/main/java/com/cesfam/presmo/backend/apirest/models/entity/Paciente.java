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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pacientes")
public class Paciente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min = 4, max = 50, message = "el tamaño debe estar entre 4 y 50 caracteres")
	@Column(nullable = false)
	private String nombre;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min = 4, max = 50, message = "el tamaño debe estar entre 4 y 50 caracteres")
	@Column(name = "apellido_paterno", nullable = false)
	private String apellidoPaterno;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min = 4, max = 50, message = "el tamaño debe estar entre 4 y 50 caracteres")
	@Column(name = "apellido_materno", nullable = false)
	private String apellidoMaterno;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min = 9, max = 12, message = "el tamaño debe estar entre 9 y 12 caracteres")
	@Column(nullable = false, unique = true)
	private String rut;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Column(nullable = false, unique = true)
	private String region;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Column(nullable = false, unique = true)
	private String comuna;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Column(nullable = false, unique = true)
	private String nacionalidad;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Column(name = "fecha_nacimiento", nullable = false)
	private Date fechaNacimiento;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min = 8, max = 8, message = "el número de celular debe tener 8 digitos")
	@Column(name = "numero_celular", nullable = false)
	private int numeroCelular;
	@Size(min = 8, max = 8, message = "el número de teléfono debe tener 8 digitos")
	@Column(name = "telefono_fijo", nullable = true)
	private int telefonoFijo;
	@NotEmpty(message = "El campo no puede estar vacío puede estar vacío")
	@Size(min = 9, max = 9, message = "el rut debe tener un tamaño de 9 caracteres")
	@Column(name = "rut_tutor", nullable = false, unique = true)
	private String rutTutor;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min = 4, max = 50, message = "el tamaño debe estar entre 4 y 50 caracteres")
	@Column(name = "nombre_tutor", nullable = false)
	private String nombreTutor;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Email(message = "debe contener una dirección de correo electrónico valida")
	@Column(name = "email_tutor", unique = true, nullable = false)
	private String emailTutor;
	private String foto;
	
	@NotNull(message="Se debe indicar el sexo del paciente")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="sexo_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Sexo sexo;
	
	@NotNull(message="Se debe indicar el tipo de previsión del paciente")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="prevision_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Prevision prevision;
	
	@NotNull(message="Se debe indicar el carnet del paciente")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="carnet_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Carnet carnet;

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

	public String getRutTutor() {
		return rutTutor;
	}

	public void setRutTutor(String rutTutor) {
		this.rutTutor = rutTutor;
	}

	public String getNombreTutor() {
		return nombreTutor;
	}

	public void setNombreTutor(String nombreTutor) {
		this.nombreTutor = nombreTutor;
	}

	public String getEmailTutor() {
		return emailTutor;
	}

	public void setEmailTutor(String emailTutor) {
		this.emailTutor = emailTutor;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Prevision getPrevision() {
		return prevision;
	}

	public void setPrevision(Prevision prevision) {
		this.prevision = prevision;
	}

	public Carnet getCarnet() {
		return carnet;
	}

	public void setCarnet(Carnet carnet) {
		this.carnet = carnet;
	}

}
