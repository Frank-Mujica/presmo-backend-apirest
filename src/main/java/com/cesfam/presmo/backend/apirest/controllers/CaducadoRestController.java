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

import com.cesfam.presmo.backend.apirest.models.entity.Caducado;
import com.cesfam.presmo.backend.apirest.models.entity.Farmaceutico;
import com.cesfam.presmo.backend.apirest.models.entity.MotivoCaducado;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaDetalle;
import com.cesfam.presmo.backend.apirest.models.entity.Partida;
import com.cesfam.presmo.backend.apirest.models.services.ICaducadoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CaducadoRestController {
	
	@Autowired
	private ICaducadoService caducadoService;
	
	@GetMapping("/caducados")
	public List<Caducado> index(){
		return caducadoService.findAll();
	}
	
	@GetMapping("/caducados/page/{page}")
	public Page<Caducado> index(@PathVariable Integer page){
		return caducadoService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/caducados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Caducado caducado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			caducado = caducadoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar el artículo caducado");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(caducado == null) {
			response.put("mensaje", "La artículo caducado ID: ".concat(id.toString().concat(" no se encuentra registrada")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Caducado>(caducado, HttpStatus.OK);
	}
	
	@Secured("ROLE_MEDICO")
	@PostMapping("/caducados")
	public ResponseEntity<?> create(@Valid @RequestBody Caducado caducado, BindingResult result) {
		
		Caducado caducadoNew = null;
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
			caducadoNew = caducadoService.save(caducado);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el artículo como caducado");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El artículo ha sido registrado como caducado con éxito!");
		response.put("Caducado", caducadoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/caducados/farmaceuticos")
	public List<Farmaceutico> listarFarmaceuticos(){
		return caducadoService.findAllFarmaceuticos();
	}
	
	@GetMapping("/caducados/pacientes")
	public List<MotivoCaducado> listarMotivo_Caducados(){
		return caducadoService.findAllMotivo_Caducados();
	}
	
	@GetMapping("/caducados/receta_detalles")
	public List<RecetaDetalle> listarReceta_Detalles(){
		return caducadoService.findAllReceta_Detalles();
	}
	
	@GetMapping("/caducados/partidas")
	public List<Partida> listarPartidas(){
		return caducadoService.findAllPartidas();
	}
	
}

