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
/*import org.springframework.web.bind.annotation.DeleteMapping;*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
/*import org.springframework.web.bind.annotation.PutMapping;*/
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesfam.presmo.backend.apirest.models.entity.TratamientosLargos;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;
import com.cesfam.presmo.backend.apirest.models.services.ITratamientosLargosService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class TratamientosLargosRestController {
	
	@Autowired
	private ITratamientosLargosService tratamientosLargosService;
	
	@GetMapping("/tratamientos_largos")
	public List<TratamientosLargos> index(){
		return tratamientosLargosService.findAll();
	}
	
	@GetMapping("/tratamientos_largos/page/{page}")
	public Page<TratamientosLargos> index(@PathVariable Integer page){
		return tratamientosLargosService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/tratamientos_largos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		TratamientosLargos tratamientosLargos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			tratamientosLargos = tratamientosLargosService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar el tratamiento");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(tratamientosLargos == null) {
			response.put("mensaje", "El tratamiento ID: ".concat(id.toString().concat(" no se encuentra registrada")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<TratamientosLargos>(tratamientosLargos, HttpStatus.OK);
	}
	
	/*@Secured("ROLE_MEDICO")*/
	@PostMapping("/tratamientos_largos")
	public ResponseEntity<?> create(@Valid @RequestBody TratamientosLargos tratamientosLargos, BindingResult result) {
		
		TratamientosLargos tratamientosLargosNew = null;
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
			tratamientosLargosNew = tratamientosLargosService.save(tratamientosLargos);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el tratamiento");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El tratamiento ha sido registrado como caducado con éxito!");
		response.put("Tratamientos Largos", tratamientosLargosNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/tratamientos_largos/receta_cebeceras")
	public List<RecetaCabecera> listarReceta_Cabeceras(){
		return tratamientosLargosService.findAllReceta_Cabeceras();
	}
	
}


