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

import com.cesfam.presmo.backend.apirest.models.entity.Medico;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;
import com.cesfam.presmo.backend.apirest.models.entity.Especialidad;
import com.cesfam.presmo.backend.apirest.models.entity.Usuario;
import com.cesfam.presmo.backend.apirest.models.services.IMedicoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class MedicoRestController {
	
	@Autowired
	private IMedicoService medicoService;
	
	private final Logger log = LoggerFactory.getLogger(PacienteRestController.class);
	
	@GetMapping("/medicos")
	public List<Medico> index(){
		return medicoService.findAll();
	}
	
	@GetMapping("/medicos/page/{page}")
	public Page<Medico> index(@PathVariable Integer page){
		return medicoService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/medicos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Medico medico = null;
		Map<String, Object> response = new HashMap<>();
		try {
			medico = medicoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar al médico");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(medico == null) {
			response.put("mensaje", "El médico ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Medico>(medico, HttpStatus.OK);
	}
	
	@Secured("Administrador")
	@PostMapping("/medicos")
	public ResponseEntity<?> create(@Valid @RequestBody Medico medico, BindingResult result) {
		
		Medico medicoNew = null;
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
			medicoNew = medicoService.save(medico);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar al médico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El médico ha sido registrado con éxito!");
		response.put("médico", medicoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("Administrador")
	@PutMapping("/medicos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Medico medico, BindingResult result, @PathVariable Long id) {
		
		Medico medicoActual = medicoService.findById(id);
		Medico medicoUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(medico == null) {
			response.put("mensaje", "Error: no se pudo editar, el médico ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

		medicoActual.setNombre(medico.getNombre());
		medicoActual.setApellidoPaterno(medico.getApellidoPaterno());
		medicoActual.setApellidoMaterno(medico.getApellidoPaterno());
		medicoActual.setRut(medico.getRut());
		medicoActual.setFechaNacimiento(medico.getFechaNacimiento());
		medicoActual.setNumeroCelular(medico.getNumeroCelular());
		medicoActual.setTelefonoFijo(medico.getTelefonoFijo());
		medicoActual.setSexo(medico.getSexo());
		medicoActual.setEspecialidad(medico.getEspecialidad());
		medicoActual.setUsuario(medico.getUsuario());
		
		
		medicoUpdate = medicoService.save(medicoActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar al médico");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El médico ha sido actualizado con éxito!");
		response.put("médico", medicoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("Administrador")
	@DeleteMapping("/medicos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Medico medico = medicoService.findById(id);
			String nombreFotoAnterior = medico.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File fileFotoAnterior = rutaFotoAnterior.toFile();
				if(fileFotoAnterior.exists() && fileFotoAnterior.canRead()) {
					fileFotoAnterior.delete();
				}
			}
			
		medicoService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar al médico");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "el médico ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@Secured({"Administrador"})
	@PostMapping("/medicos/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Medico medico = medicoService.findById(id);
		
		if(!file.isEmpty()) {
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
			Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();	
			log.info(filePath.toString());
			
			try {
				Files.copy(file.getInputStream(), filePath);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del médico "+ fileName);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = medico.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File fileFotoAnterior = rutaFotoAnterior.toFile();
				if(fileFotoAnterior.exists() && fileFotoAnterior.canRead()) {
					fileFotoAnterior.delete();
				}
			}
			
			medico.setFoto(fileName);
			
			medicoService.save(medico);
			
			response.put("médico", medico);
			response.put("mensaje", "Has subido correctamente la imagen: " + fileName);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/medicos/img/{nombreFoto:.+}")
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
	
	@GetMapping("/medicos/sexos")
	public List<Sexo> listarSexos(){
		return medicoService.findAllSexos();
	}
	
	@GetMapping("/medicos/especialidades")
	public List<Especialidad> listarEspecialidades(){
		return medicoService.findAllEspecialidades();
	}
	
	@GetMapping("/medicos/usuarios")
	public List<Usuario> listarUsuarios(){
		return medicoService.findAllUsuarios();
	}
	
}

