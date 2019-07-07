package com.cesfam.presmo.backend.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cesfam.presmo.backend.apirest.models.entity.Carnet;
import com.cesfam.presmo.backend.apirest.models.entity.Comuna;
import com.cesfam.presmo.backend.apirest.models.entity.EstadoCivil;
import com.cesfam.presmo.backend.apirest.models.entity.Nacionalidad;
import com.cesfam.presmo.backend.apirest.models.entity.Paciente;
import com.cesfam.presmo.backend.apirest.models.entity.Prevision;
import com.cesfam.presmo.backend.apirest.models.entity.Region;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;
import com.cesfam.presmo.backend.apirest.models.services.IPacienteService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class PacienteRestController {
	
	@Autowired
	private IPacienteService pacienteService;
	
	private final Logger log = LoggerFactory.getLogger(PacienteRestController.class);
	
	@GetMapping("/pacientes")
	public List<Paciente> index(){
		return pacienteService.findAll();
	}
	
	@GetMapping("/pacientes/page/{page}")
	public Page<Paciente> index(@PathVariable Integer page){
		return pacienteService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/pacientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Paciente paciente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			paciente = pacienteService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar al paciente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(paciente == null) {
			response.put("mensaje", "El paciente ID: ".concat(id.toString().concat(" no se encuentra registrado")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}
	
	@Secured("ROLE_MEDICO")
	@PostMapping("/pacientes")
	public ResponseEntity<?> create(@Valid @RequestBody Paciente paciente, BindingResult result) {
		
		Paciente pacienteNew = null;
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
			pacienteNew = pacienteService.save(paciente);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro del paciente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El paciente ha sido registrado con éxito!");
		response.put("paciente", pacienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_MEDICO")
	@PutMapping("/pacientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Paciente paciente, BindingResult result, @PathVariable Long id) {
		
		Paciente pacienteActual = pacienteService.findById(id);
		Paciente pacienteUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(paciente == null) {
			response.put("mensaje", "Error: no se pudo editar, el paciente ID: ".concat(id.toString().concat(" no se encuentra registrado")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

		pacienteActual.setNombre(paciente.getNombre());
		pacienteActual.setApellidoPaterno(paciente.getApellidoPaterno());
		pacienteActual.setApellidoMaterno(paciente.getApellidoPaterno());
		pacienteActual.setRut(paciente.getRut());
		pacienteActual.setFechaNacimiento(paciente.getFechaNacimiento());
		pacienteActual.setNumeroCelular(paciente.getNumeroCelular());
		pacienteActual.setTelefonoFijo(paciente.getTelefonoFijo());
		pacienteActual.setRutTutor(paciente.getRutTutor());
		pacienteActual.setNombreTutor(paciente.getNombreTutor());
		pacienteActual.setEmailTutor(paciente.getEmailTutor());
		pacienteActual.setSexo(paciente.getSexo());
		pacienteActual.setPrevision(paciente.getPrevision());
		pacienteActual.setCarnet(paciente.getCarnet());
		pacienteActual.setRegion(paciente.getRegion());
		pacienteActual.setComuna(paciente.getComuna());
		pacienteActual.setNacionalidad(paciente.getNacionalidad());
		
		
		pacienteUpdate = pacienteService.save(pacienteActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar al paciente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El paciente ha sido actualizado con éxito!");
		response.put("paciente", pacienteUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_MEDICO")
	@DeleteMapping("/pacientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Paciente paciente = pacienteService.findById(id);
			String nombreFotoAnterior = paciente.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File fileFotoAnterior = rutaFotoAnterior.toFile();
				if(fileFotoAnterior.exists() && fileFotoAnterior.canRead()) {
					fileFotoAnterior.delete();
				}
			}
			
		pacienteService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar al paciente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "el paciente ha sido eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@Secured({"ROLE_MEDICO"})
	@PostMapping("/pacientes/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Paciente paciente = pacienteService.findById(id);
		
		if(!file.isEmpty()) {
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
			Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();	
			log.info(filePath.toString());
			
			try {
				Files.copy(file.getInputStream(), filePath);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del paciente "+ fileName);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = paciente.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File fileFotoAnterior = rutaFotoAnterior.toFile();
				if(fileFotoAnterior.exists() && fileFotoAnterior.canRead()) {
					fileFotoAnterior.delete();
				}
			}
			
			paciente.setFoto(fileName);
			
			pacienteService.save(paciente);
			
			response.put("paciente", paciente);
			response.put("mensaje", "Has subido correctamente la imagen: " + fileName);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/pacientes/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		
		Path filePath = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		
		Resource recurso = null;
		
		log.info(filePath.toString());
		try {
			recurso = new UrlResource(filePath.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error al cargar la imagen: " + nombreFoto);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@GetMapping("/pacientes/estados_civiles")
	public List<EstadoCivil> listarEstados_civiles(){
		return pacienteService.findAllEstados_civiles();
	}
	
	@GetMapping("/pacientes/sexos")
	public List<Sexo> listarSexos(){
		return pacienteService.findAllSexos();
	}
	
	@GetMapping("/pacientes/previsiones")
	public List<Prevision> listarPrevisiones(){
		return pacienteService.findAllPrevisiones();
	}
	
	@GetMapping("/pacientes/carnets")
	public List<Carnet> listarCarnets(){
		return pacienteService.findAllCarnets();
	}
	
	@GetMapping("/pacientes/regiones")
	public List<Region> listarRegiones(){
		return pacienteService.findAllRegiones();
	}
	
	@GetMapping("/pacientes/comunas")
	public List<Comuna> listarComunas(){
		return pacienteService.findAllComunas();
	}
	
	@GetMapping("/pacientes/nacionalidades")
	public List<Nacionalidad> listarNacionalidades(){
		return pacienteService.findAllNacionalidades();
	}
	
}

