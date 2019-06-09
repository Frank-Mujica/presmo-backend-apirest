package com.cesfam.presmo.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.services.IArticuloService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ArticuloRestController {
	
	@Autowired
	private IArticuloService articuloService;
	
	@GetMapping("/articulos")
	public List<Articulo> index(){
		return articuloService.findAll();
	}
	
	@GetMapping("/articulos/{id}")
	public Articulo show(@PathVariable Long id) {
		return articuloService.findById(id);
	}
	
	@PostMapping("/articulos")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo create(@RequestBody Articulo articulo) {
		return articuloService.save(articulo);
	}
	
	@PutMapping("/articulos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo update(@RequestBody Articulo articulo, @PathVariable Long id) {
		Articulo articuloActual = articuloService.findById(id);
		
		articuloActual.setIdTipo(articulo.getIdTipo());
		articuloActual.setIdFabricante(articulo.getIdFabricante());
		articuloActual.setDescripcion(articulo.getDescripcion());
		articuloActual.setComponentes(articulo.getComponentes());
		articuloActual.setContenido(articulo.getContenido());
		articuloActual.setGramaje(articulo.getGramaje());
		articuloActual.setStock(articulo.getStock());
		
		return articuloService.save(articuloActual);
	}
	
	@DeleteMapping("/articulos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		articuloService.delete(id);
	}

}
