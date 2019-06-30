package com.cesfam.presmo.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	@Column(name = "id_sexo", nullable = false)
	private int idSexo;
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
	@Column(name = "id_prevision", nullable = false)
	private int idPrevision;
	private String foto;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "pacientes_sexo", joinColumns = @JoinColumn(name = "paciente_id"), inverseJoinColumns = @JoinColumn(name = "sexo_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "paciente_id", "sexo_id" }) })
	private List<Sexo> sexo;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "pacientes_prevision", joinColumns = @JoinColumn(name = "paciente_id"), inverseJoinColumns = @JoinColumn(name = "prevision_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "paciente_id", "prevision_id" }) })
	private List<Prevision> prevision;

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
	
	public int getIdSexo() {
		return idSexo;
	}
	
	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
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

	public int getIdPrevision() {
		return idPrevision;
	}

	public void setIdPrevision(int idPrevision) {
		this.idPrevision = idPrevision;
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

	public List<Sexo> getSexo() {
		return sexo;
	}

	public void setSexo(List<Sexo> sexo) {
		this.sexo = sexo;
	}

	public void setSexo(int idSexo) {
		this.idSexo = idSexo;
	}

	public List<Prevision> getPrevision() {
		return prevision;
	}

	public void setPrevision(List<Prevision> prevision) {
		this.prevision = prevision;
	}

}
