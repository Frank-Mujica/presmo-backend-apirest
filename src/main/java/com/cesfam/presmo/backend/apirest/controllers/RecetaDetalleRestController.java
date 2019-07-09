package com.cesfam.presmo.backend.apirest.controllers;

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
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
/*import org.springframework.web.bind.annotation.DeleteMapping;*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
/*import org.springframework.web.bind.annotation.PutMapping;*/
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaDetalle;
import com.cesfam.presmo.backend.apirest.models.services.IRecetaDetalleService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class RecetaDetalleRestController {
	
	@Autowired
	private IRecetaDetalleService recetaDetalleService;
	
	@GetMapping("/receta_detalles")
	public List<RecetaDetalle> index(){
		return recetaDetalleService.findAll();
	}
	
	@GetMapping("/receta_detalles/page/{page}")
	public Page<RecetaDetalle> index(@PathVariable Integer page){
		return recetaDetalleService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/receta_detalles/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		RecetaDetalle recetaDetalle = null;
		Map<String, Object> response = new HashMap<>();
		try {
			recetaDetalle = recetaDetalleService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar el detalle de la receta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(recetaDetalle == null) {
			response.put("mensaje", "La receta detalle ID: ".concat(id.toString().concat(" no se encuentra registrada")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<RecetaDetalle>(recetaDetalle, HttpStatus.OK);
	}
	
	@Secured("ROLE_MEDICO")
	@PostMapping("/receta_detalles")
	public ResponseEntity<?> create(@Valid @RequestBody RecetaDetalle recetaDetalle, BindingResult result) {
		
		RecetaDetalle recetaDetalleNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errores", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			recetaDetalleNew = recetaDetalleService.save(recetaDetalle);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro del detalle de la receta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El detalle de la receta ha sido registrada con éxito!");
		response.put("Receta Cabecera", recetaDetalleNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/receta_detalles/articulos")
	public List<Articulo> listarArticulos(){
		return recetaDetalleService.findAllArticulos();
	}
	
	@GetMapping("/receta_detalles/receta_cabeceras")
	public List<RecetaCabecera> listarReceta_cabeceras(){
		return recetaDetalleService.findAllReceta_cabeceras();
	}
	
}
