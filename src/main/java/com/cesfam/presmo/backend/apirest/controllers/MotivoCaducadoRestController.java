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

import com.cesfam.presmo.backend.apirest.models.entity.MotivoCaducado;
import com.cesfam.presmo.backend.apirest.models.services.IMotivoCaducadoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class MotivoCaducadoRestController {
	
	@Autowired
	private IMotivoCaducadoService motivoCaducadoService;
	
	@GetMapping("/motivo_caducados")
	public List<MotivoCaducado> index(){
		return motivoCaducadoService.findAll();
	}
	
	@GetMapping("/motivo_caducados/page/{page}")
	public Page<MotivoCaducado> index(@PathVariable Integer page){
		return motivoCaducadoService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/motivo_caducados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		MotivoCaducado motivoCaducado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			motivoCaducado = motivoCaducadoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar el motivo de caducación");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(motivoCaducado == null) {
			response.put("mensaje", "La artículo motivo caducado ID: ".concat(id.toString().concat(" no se encuentra registrada")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MotivoCaducado>(motivoCaducado, HttpStatus.OK);
	}
	
	@Secured("ROLE_MEDICO")
	@PostMapping("/receta_cabeceras")
	public ResponseEntity<?> create(@Valid @RequestBody MotivoCaducado motivoCaducado, BindingResult result) {
		
		MotivoCaducado motivoCaducadoNew = null;
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
			motivoCaducadoNew = motivoCaducadoService.save(motivoCaducado);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el motivo de caducación");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El motivo de caducación ha sido registrado con éxito!");
		response.put("Motivo Caducado", motivoCaducadoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}


