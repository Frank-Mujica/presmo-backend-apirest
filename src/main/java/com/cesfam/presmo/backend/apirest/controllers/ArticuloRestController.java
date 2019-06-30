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

import com.cesfam.presmo.backend.apirest.models.entity.Articulo;
import com.cesfam.presmo.backend.apirest.models.entity.Fabricante;
import com.cesfam.presmo.backend.apirest.models.entity.Sexo;
import com.cesfam.presmo.backend.apirest.models.entity.Tipo;
import com.cesfam.presmo.backend.apirest.models.services.IArticuloService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ArticuloRestController {
	
	@Autowired
	private IArticuloService articuloService;
	
	private final Logger log = LoggerFactory.getLogger(ArticuloRestController.class);
	
	@GetMapping("/articulos")
	public List<Articulo> index(){
		return articuloService.findAll();
	}
	
	@GetMapping("/articulos/page/{page}")
	public Page<Articulo> index(@PathVariable Integer page){
		return articuloService.findAll(PageRequest.of(page, 6));
	}
	
	@GetMapping("/articulos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Articulo articulo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			articulo = articuloService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al buscar el articulo");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(articulo == null) {
			response.put("mensaje", "El articulo ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
	}
	
	@Secured("Farmaceutico")
	@PostMapping("/articulos")
	public ResponseEntity<?> create(@Valid @RequestBody Articulo articulo, BindingResult result) {
		
		Articulo articuloNew = null;
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
			articuloNew = articuloService.save(articulo);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro del articulo");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El articulo ha sido registrado con éxito!");
		response.put("articulo", articuloNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("Farmaceutico")
	@PutMapping("/articulos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Articulo articulo, BindingResult result, @PathVariable Long id) {
		
		Articulo articuloActual = articuloService.findById(id);
		Articulo articuloUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(articulo == null) {
			response.put("mensaje", "Error: no se pudo editar, el articulo ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

		articuloActual.setDescripcion(articulo.getDescripcion());
		articuloActual.setComponentes(articulo.getComponentes());
		articuloActual.setContenido(articulo.getContenido());
		articuloActual.setGramaje(articulo.getGramaje());
		articuloActual.setStock(articulo.getStock());
		articuloActual.setTipo(articulo.getTipo());
		articuloActual.setFabricante(articulo.getFabricante());
		
		
		articuloUpdate = articuloService.save(articuloActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el articulo");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El articulo ha sido actualizado con éxito!");
		response.put("articulo", articuloUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("Farmaceutico")
	@DeleteMapping("/articulos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Articulo articulo = articuloService.findById(id);
			String nombreFotoAnterior = articulo.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File fileFotoAnterior = rutaFotoAnterior.toFile();
				if(fileFotoAnterior.exists() && fileFotoAnterior.canRead()) {
					fileFotoAnterior.delete();
				}
			}
			
		articuloService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el articulo");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "el articulo ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@Secured({"Farmaceutico", "Medico"})
	@PostMapping("/articulos/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Articulo articulo = articuloService.findById(id);
		
		if(!file.isEmpty()) {
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
			Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();	
			log.info(filePath.toString());
			
			try {
				Files.copy(file.getInputStream(), filePath);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del articulo "+ fileName);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = articulo.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File fileFotoAnterior = rutaFotoAnterior.toFile();
				if(fileFotoAnterior.exists() && fileFotoAnterior.canRead()) {
					fileFotoAnterior.delete();
				}
			}
			
			articulo.setFoto(fileName);
			
			articuloService.save(articulo);
			
			response.put("articulo", articulo);
			response.put("mensaje", "Has subido correctamente la imagen: " + fileName);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/articulos/img/{nombreFoto:.+}")
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
	
	@GetMapping("/articulos/tipos")
	public List<Tipo> listarTipos(){
		return articuloService.findAllTipos();
	}
	
	@GetMapping("/articulos/fabricantes")
	public List<Fabricante> listarFabricantes(){
		return articuloService.findAllFabricantes();
	}
}
