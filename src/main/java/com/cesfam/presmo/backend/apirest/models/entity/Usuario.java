package com.cesfam.presmo.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity 
@Table(name="usuarios")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=4, max=30, message="el tamaño debe estar entre 4 y 30 caracteres")
	@Column(length=30, nullable=false)
	private String nombre;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=4, max=50, message="el tamaño debe estar entre 4 y 50 caracteres")
	@Column(length=50, nullable=false)
	private String apellidos;
	@NotEmpty(message = "no puede estar vacío")
	@Email(message="debe contener una dirección de correo electrónico valida")
	@Column(unique=true, nullable=false)
	private String email;
	@NotEmpty(message = "no puede estar vacío")
	@Column(unique=true, nullable=false)
	private String password;
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=4, max=25, message="el tamaño debe estar entre 4 y 25 caracteres")
	@Column(name="tipo_usuario", nullable=false)
	private String tipoUsuario;
	@Column(name="created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}
	
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipo) {
		this.tipoUsuario = tipo;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
