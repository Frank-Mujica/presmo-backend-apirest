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

import com.cesfam.presmo.backend.apirest.models.entity.Farmaceutico;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;
import com.cesfam.presmo.backend.apirest.models.entity.Cargo;
import com.cesfam.presmo.backend.apirest.models.entity.Farmacia;
import com.cesfam.presmo.backend.apirest.models.entity.Usuario;
import com.cesfam.presmo.backend.apirest.models.services.IFarmaceuticoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class FarmaceuticoRestController {
	
	@Autowired
	private IFarmaceuticoService farmaceuticoService;
	
	private final Logger log = LoggerFactory.getLogger(PacienteRestController.class);
	
	@GetMapping("/farmaceuticos")
	public List<Farmaceutico> index(){
		return farmaceuticoService.findAll();
	}
	
	@GetMapping("/farmaceuticos/page/{page}")
	public Page<Farmaceutico> index(@PathVariable Integer page){
		return farmaceuticoService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/farmaceuticos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Farmaceutico farmaceutico = null;
		Map<String, Object> response = new HashMap<>();
		try {
			farmaceutico = farmaceuticoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar al farmacéutico");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(farmaceutico == null) {
			response.put("mensaje", "El farmacéutico ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Farmaceutico>(farmaceutico, HttpStatus.OK);
	}
	
	@Secured("Administrador")
	@PostMapping("/farmaceuticos")
	public ResponseEntity<?> create(@Valid @RequestBody Farmaceutico farmaceutico, BindingResult result) {
		
		Farmaceutico farmaceuticoNew = null;
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
			farmaceuticoNew = farmaceuticoService.save(farmaceutico);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar al farmacéutico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El farmacéutico ha sido registrado con éxito!");
		response.put("farmacéutico", farmaceuticoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("Administrador")
	@PutMapping("/farmaceuticos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Farmaceutico farmaceutico, BindingResult result, @PathVariable Long id) {
		
		Farmaceutico farmaceuticoActual = farmaceuticoService.findById(id);
		Farmaceutico farmaceuticoUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(farmaceutico == null) {
			response.put("mensaje", "Error: no se pudo editar, el farmacéutico ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

		farmaceuticoActual.setNombre(farmaceutico.getNombre());
		farmaceuticoActual.setApellidoPaterno(farmaceutico.getApellidoPaterno());
		farmaceuticoActual.setApellidoMaterno(farmaceutico.getApellidoPaterno());
		farmaceuticoActual.setRut(farmaceutico.getRut());
		farmaceuticoActual.setFechaNacimiento(farmaceutico.getFechaNacimiento());
		farmaceuticoActual.setNumeroCelular(farmaceutico.getNumeroCelular());
		farmaceuticoActual.setTelefonoFijo(farmaceutico.getTelefonoFijo());
		farmaceuticoActual.setSexo(farmaceutico.getSexo());
		farmaceuticoActual.setCargo(farmaceutico.getCargo());
		farmaceuticoActual.setFarmacia(farmaceutico.getFarmacia());
		farmaceuticoActual.setUsuario(farmaceutico.getUsuario());
		
		
		farmaceuticoUpdate = farmaceuticoService.save(farmaceuticoActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar al farmacéutico");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El farmacéutico ha sido actualizado con éxito!");
		response.put("farmacéutico", farmaceuticoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("Administrador")
	@DeleteMapping("/farmaceuticos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Farmaceutico farmaceutico = farmaceuticoService.findById(id);
			String nombreFotoAnterior = farmaceutico.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File fileFotoAnterior = rutaFotoAnterior.toFile();
				if(fileFotoAnterior.exists() && fileFotoAnterior.canRead()) {
					fileFotoAnterior.delete();
				}
			}
			
		farmaceuticoService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar al farmacéutico");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "el farmacéutico ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@Secured({"Administrador"})
	@PostMapping("/farmaceuticos/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Farmaceutico farmaceutico = farmaceuticoService.findById(id);
		
		if(!file.isEmpty()) {
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
			Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();	
			log.info(filePath.toString());
			
			try {
				Files.copy(file.getInputStream(), filePath);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del farmacéutico "+ fileName);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = farmaceutico.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File fileFotoAnterior = rutaFotoAnterior.toFile();
				if(fileFotoAnterior.exists() && fileFotoAnterior.canRead()) {
					fileFotoAnterior.delete();
				}
			}
			
			farmaceutico.setFoto(fileName);
			
			farmaceuticoService.save(farmaceutico);
			
			response.put("farmacéutico", farmaceutico);
			response.put("mensaje", "Has subido correctamente la imagen: " + fileName);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/farmaceuticos/img/{nombreFoto:.+}")
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
	
	@GetMapping("/farmaceuticos/sexos")
	public List<Sexo> listarSexos(){
		return farmaceuticoService.findAllSexos();
	}
	
	@GetMapping("/farmaceuticos/cargos")
	public List<Cargo> listarCargos(){
		return farmaceuticoService.findAllCargos();
	}
	
	@GetMapping("/farmaceuticos/farmacias")
	public List<Farmacia> listarFarmacias(){
		return farmaceuticoService.findAllFarmacias();
	}
	
	@GetMapping("/farmaceuticos/usuarios")
	public List<Usuario> listarUsuarios(){
		return farmaceuticoService.findAllUsuarios();
	}
	
}

