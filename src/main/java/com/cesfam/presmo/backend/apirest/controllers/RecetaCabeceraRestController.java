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

import com.cesfam.presmo.backend.apirest.models.entity.Medico;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;
import com.cesfam.presmo.backend.apirest.models.entity.RecetaCabecera;
import com.cesfam.presmo.backend.apirest.models.services.IRecetaCabeceraService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class RecetaCabeceraRestController {
	
	@Autowired
	private IRecetaCabeceraService recetaCabeceraService;
	
	@GetMapping("/receta_cabeceras")
	public List<RecetaCabecera> index(){
		return recetaCabeceraService.findAll();
	}
	
	@GetMapping("/receta_cabeceras/page/{page}")
	public Page<RecetaCabecera> index(@PathVariable Integer page){
		return recetaCabeceraService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/receta_cabeceras/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		RecetaCabecera recetaCabecera = null;
		Map<String, Object> response = new HashMap<>();
		try {
			recetaCabecera = recetaCabeceraService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar la cabecera de la receta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(recetaCabecera == null) {
			response.put("mensaje", "La receta cabecera ID: ".concat(id.toString().concat(" no se encuentra registrada")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<RecetaCabecera>(recetaCabecera, HttpStatus.OK);
	}
	
	@Secured("ROLE_MEDICO")
	@PostMapping("/receta_cabeceras")
	public ResponseEntity<?> create(@Valid @RequestBody RecetaCabecera recetaCabecera, BindingResult result) {
		
		RecetaCabecera recetaCabeceraNew = null;
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
			recetaCabeceraNew = recetaCabeceraService.save(recetaCabecera);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro de la cabecera de la receta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cabecera de la receta ha sido registrada con éxito!");
		response.put("Receta Cabecera", recetaCabeceraNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/receta_cabeceras/medicos")
	public List<Medico> listarMedicos(){
		return recetaCabeceraService.findAllMedicos();
	}
	
	@GetMapping("/receta_cabeceras/pacientes")
	public List<Paciente> listarPacientes(){
		return recetaCabeceraService.findAllPacientes();
	}
	
}
