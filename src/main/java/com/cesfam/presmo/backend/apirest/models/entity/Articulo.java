package com.cesfam.presmo.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@NotNull(message="Se debe indicar el tipo de artículo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Tipo tipo;
	
	@NotNull(message="Se debe indicar el fabricante del artículo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_fabricante")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Fabricante fabricante;

	public Long getId() {
		return id;
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
