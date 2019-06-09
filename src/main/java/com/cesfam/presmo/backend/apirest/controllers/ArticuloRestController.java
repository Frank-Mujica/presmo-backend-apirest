package com.cesfam.presmo.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/articulos/page/{page}")
	public Page<Articulo> index(@PathVariable Integer page){
		return articuloService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/articulos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Articulo articulo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			articulo = articuloService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(articulo == null) {
			response.put("mensaje", "El articulo ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
	}
	
	@PostMapping("/articulos")
	public ResponseEntity<?> create(@Valid @RequestBody Articulo articulo, BindingResult result) {
		
		Articulo articuloNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			articuloNew = articuloService.save(articulo);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido registrado con éxito!");
		response.put("articulo", articuloNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/articulos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Articulo articulo, BindingResult result, @PathVariable Long id) {
		
		Articulo articuloActual = articuloService.findById(id);
		Articulo articuloUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(articulo == null) {
			response.put("mensaje", "Error: no se pudo editar, el articulo ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		articuloActual.setIdTipo(articulo.getIdTipo());
		articuloActual.setIdFabricante(articulo.getIdFabricante());
		articuloActual.setDescripcion(articulo.getDescripcion());
		articuloActual.setComponentes(articulo.getComponentes());
		articuloActual.setContenido(articulo.getContenido());
		articuloActual.setGramaje(articulo.getGramaje());
		articuloActual.setStock(articulo.getStock());
		
		
		articuloUpdate = articuloService.save(articuloActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el articulo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("articulo", articuloUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/articulos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
		articuloService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el articulo de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "el articulo ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

}
