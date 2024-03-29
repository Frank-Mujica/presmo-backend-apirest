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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@NotNull(message = "El campo no puede estar vacío")
	@Column(name = "fecha_nacimiento", nullable = false)
	@JsonFormat(pattern= "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Pattern(regexp="^[0-9]+$", message = "El número de celular debe contener dígitos númericos")
	@Size(min = 8, max = 9, message = "El número de celular debe contener de 8 a 9 caracteres númericos")
	@Column(name = "numero_celular", nullable = false)
	private String numeroCelular;
	@Pattern(regexp="^[0-9]+$")
	@Size(min = 8, max = 9, message = "El número de teléfono debe contener de 8 a 9 caracteres númericos")
	@Column(name = "telefono_fijo", nullable = true)
	private String telefonoFijo;
	@Size(min = 9, max = 12, message = "el rut debe tener un tamaño de 9 caracteres")
	@Column(name = "rut_tutor", nullable = true)
	private String rutTutor;
	@Size(min = 4, max = 50, message = "el tamaño debe estar entre 4 y 50 caracteres")
	@Column(name = "nombre_tutor", nullable = true)
	private String nombreTutor;
	@Email(message = "debe contener una dirección de correo electrónico valida")
	@Column(name = "email_tutor", unique = true, nullable = true)
	private String emailTutor;
	@Column(name = "foto_paciente", nullable = true)
	private String foto;
	
	@NotNull(message="Se debe indicar el estado civil del paciente")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="estado_civil_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private EstadoCivil estadoCivil;
	
	@NotNull(message="Se debe indicar el sexo del paciente")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sexo_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Sexo sexo;
	
	@NotNull(message="Se debe indicar la previsión del paciente")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prevision_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Prevision prevision;
	
	@NotNull(message="Se debe indicar el carnet del paciente")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="carnet_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Carnet carnet;
	
	@NotNull(message="Se debe indicar la regón del paciente")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Region region;
	
	@NotNull(message="Se debe indicar la comuna del paciente")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="comuna_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Comuna comuna;
	
	@NotNull(message="Se debe indicar la nacionalidad del paciente")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="nacionalidad_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Nacionalidad nacionalidad;

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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

}
