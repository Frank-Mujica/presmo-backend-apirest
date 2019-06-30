package com.cesfam.presmo.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "articulos")
public class Articulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_tipo", nullable = false)
	private int idTipo;
	@Column(name = "id_fabricante", nullable = false)
	private int idFabricante;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min=4, max=50, message="el tamaño debe estar entre 4 y 50 caracteres")
	@Column(nullable = false)
	private String descripcion;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min=4, max=25, message="el tamaño debe estar entre 4 y 25 caracteres")
	@Column(nullable = false)
	private String componentes;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(min=4, max=25, message="el tamaño debe estar entre 4 y 25 caracteres")
	@Column(nullable = false)
	private String contenido;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Column(nullable = false)
	private int gramaje;
	@NotEmpty(message = "El campo no puede estar vacío")
	@Column(nullable = false)
	private int stock;	
	private String foto;

	public Long getId() {
		return id;
	}
	
	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public int getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(int idFabricante) {
		this.idFabricante = idFabricante;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getComponentes() {
		return componentes;
	}

	public void setComponentes(String componentes) {
		this.componentes = componentes;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public int getGramaje() {
		return gramaje;
	}

	public void setGramaje(int gramaje) {
		this.gramaje = gramaje;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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
