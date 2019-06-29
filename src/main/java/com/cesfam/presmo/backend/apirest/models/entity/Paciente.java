package com.cesfam.presmo.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pacientes")
public class Paciente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=4, max=50, message="el tamaño debe estar entre 4 y 50 caracteres")
	@Column(nullable = false)
	private String nombre;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=4, max=50, message="el tamaño debe estar entre 4 y 50 caracteres")
	@Column(nullable = false)
	private String apellidoPaterno;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=4, max=50, message="el tamaño debe estar entre 4 y 50 caracteres")
	@Column(nullable = false)
	private String apellidoMaterno;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=9, max=12, message="el tamaño debe estar entre 9 y 12 caracteres")
	@Column(nullable = false, unique=true)
	private String rut;
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private Date fechaNacimiento;
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private String sexo;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=9, max=12, message="el tamaño debe estar entre 9 y 12 caracteres")
	@Column(nullable = false, unique=true)
	private String rutTutor;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=4, max=50, message="el tamaño debe estar entre 4 y 50 caracteres")
	@Column(nullable = false)
	private String nombreTutor;
	@NotEmpty(message = "no puede estar vacío")
	@Email(message = "debe contener una dirección de correo electrónico valida")
	@Column(unique = true, nullable = false)
	private String emailTutor;
	@Column(name = "id_region")
	private int idRegion;
	@Column(name = "id_comuna")
	private int idComuna;
	@Column(name = "id_prevision")
	private int idPrevision;
	@Column(name = "id_nacionalidad")
	private int idNacionalidad;
	private String foto;
	
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
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
	public int getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
	public int getIdComuna() {
		return idComuna;
	}
	public void setIdComuna(int idComuna) {
		this.idComuna = idComuna;
	}
	public int getIdPrevision() {
		return idPrevision;
	}
	public void setIdPrevision(int idPrevision) {
		this.idPrevision = idPrevision;
	}
	public int getIdNacionalidad() {
		return idNacionalidad;
	}
	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
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
}
