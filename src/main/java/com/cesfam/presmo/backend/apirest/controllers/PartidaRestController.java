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
/*import org.springframework.security.access.annotation.Secured;*/
import org.springframework.validation.BindingResult;
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
import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.services.IPartidaService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class PartidaRestController {
	
	@Autowired
	private IPartidaService partidaService;
	
	@GetMapping("/partidas")
	public List<Partida> index(){
		return partidaService.findAll();
	}
	
	@GetMapping("/partidas/page/{page}")
	public Page<Partida> index(@PathVariable Integer page){
		return partidaService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/partidas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Partida partida = null;
		Map<String, Object> response = new HashMap<>();
		try {
			partida = partidaService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar la partida");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(partida == null) {
			response.put("mensaje", "La partida ID: ".concat(id.toString().concat(" no se encuentra registrada")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Partida>(partida, HttpStatus.OK);
	}
	
	/*@Secured("ROLE_FARMACEUTICO")*/
	@PostMapping("/partidas")
	public ResponseEntity<?> create(@Valid @RequestBody Partida partida, BindingResult result) {
		
		Partida partidaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			partidaNew = partidaService.save(partida);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro la partida");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El partida ha sido registrada con éxito!");
		response.put("partida", partidaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/*@Secured("ROLE_FARMACEUTICO")*/
	@PutMapping("/partidas/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Partida partida, BindingResult result, @PathVariable Long id) {
		
		Partida partidaActual = partidaService.findById(id);
		Partida partidaUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(partida == null) {
			response.put("mensaje", "Error: no se pudo editar, la partida ID: ".concat(id.toString().concat(" no se encuentra registrada")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

		partidaActual.setCantidadRestante(partida.getCantidadRestante());
		
		partidaUpdate = partidaService.save(partidaActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la partida");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La partida ha sido actualizada con éxito!");
		response.put("partida", partidaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/*@Secured("ROLE_FARMACEUTICO")*/
	@DeleteMapping("/partidas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			
			partidaService.findById(id);
			
		partidaService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la partida");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La partida ha sido eliminada con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/partidas/articulos")
	public List<Articulo> listarArticulos(){
		return partidaService.findAllArticulos();
	}
	
}
