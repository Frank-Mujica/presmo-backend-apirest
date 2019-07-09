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

import com.cesfam.presmo.backend.apirest.models.entity.Reserva;
import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;
import com.cesfam.presmo.backend.apirest.models.services.IReservaService;
import com.cesfam.presmo.backend.apirest.models.services.IPacienteService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ReservaRestController {
	
	@Autowired
	private IReservaService reservaService;
	
	@Autowired
	private IReservaService pacienteService;
	
	@GetMapping("/reservas")
	public List<Reserva> index(){
		return reservaService.findAll();
	}
	
	@GetMapping("/reservas/page/{page}")
	public Page<Reserva> index(@PathVariable Integer page){
		return reservaService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/reservas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Reserva reserva = null;
		Map<String, Object> response = new HashMap<>();
		try {
			reserva = reservaService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar la reserva");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(reserva == null) {
			response.put("mensaje", "La reserva ID: ".concat(id.toString().concat(" no se encuentra registrada")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
	}
	
	@GetMapping("/reservas/pacientes/{rut}")
	public ResponseEntity<?> show(@PathVariable String rut) {
		
		Paciente paciente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			paciente = pacienteService.findByRut(rut);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar al paciente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(paciente == null) {
			response.put("mensaje", "El paciente Rut: ".concat(rut.toString().concat(" no se encuentra registrado")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}
	
	@Secured("ROLE_MEDICO")
	@PostMapping("/reservas")
	public ResponseEntity<?> create(@Valid @RequestBody Reserva reserva, BindingResult result) {
		
		Reserva reservaNew = null;
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
			reservaNew = reservaService.save(reserva);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro de la reserva");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La reserva ha sido registrada con éxito!");
		response.put("Receta Cabecera", reservaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/reservas/articulos")
	public List<Articulo> listarArticulos(){
		return reservaService.findAllArticulos();
	}
	
	@GetMapping("/reservas/pacientes")
	public List<Paciente> listarPacientes(){
		return reservaService.findAllPacientes();
	}
	
}

