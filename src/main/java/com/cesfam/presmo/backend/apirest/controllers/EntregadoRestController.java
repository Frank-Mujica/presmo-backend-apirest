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

import com.cesfam.presmo.backend.apirest.models.entity.Entregado;
import com.cesfam.presmo.backend.apirest.models.entity.Farmaceutico;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaDetalle;
import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.services.IEntregadoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class EntregadoRestController {
	
	@Autowired
	private IEntregadoService entregadoService;
	
	@GetMapping("/entregados")
	public List<Entregado> index(){
		return entregadoService.findAll();
	}
	
	@GetMapping("/entregados/page/{page}")
	public Page<Entregado> index(@PathVariable Integer page){
		return entregadoService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/entregados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Entregado entregado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			entregado = entregadoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el artículo como entregado");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(entregado == null) {
			response.put("mensaje", "La artículo a entregar ID: ".concat(id.toString().concat(" no se encuentra registrada")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Entregado>(entregado, HttpStatus.OK);
	}
	
	@Secured("ROLE_MEDICO")
	@PostMapping("/receta_cabeceras")
	public ResponseEntity<?> create(@Valid @RequestBody Entregado entregado, BindingResult result) {
		
		Entregado entregadoNew = null;
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
			entregadoNew = entregadoService.save(entregado);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el artículo como entregado");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El artículo ha sido registrado como entregado con éxito!");
		response.put("Entregado", entregadoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/caducados/farmaceuticos")
	public List<Farmaceutico> listarFarmaceuticos(){
		return entregadoService.findAllFarmaceuticos();
	}
	
	@GetMapping("/caducados/receta_detalles")
	public List<RecetaDetalle> listarReceta_Detalles(){
		return entregadoService.findAllReceta_Detalles();
	}
	
	@GetMapping("/caducados/partidas")
	public List<Partida> listarPartidas(){
		return entregadoService.findAllPartidas();
	}
	
}

