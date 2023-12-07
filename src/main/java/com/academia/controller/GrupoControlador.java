package com.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.academia.model.Grupo;
import com.academia.service.GrupoServicio;

@RestController
public class GrupoControlador {
	
	@Autowired
	private GrupoServicio servicio;

	@GetMapping("/grupos")
	public List<Grupo> listarGrupo(){
		return servicio.listarGrupo();
	}
	
	@GetMapping("/grupo/{id}")
	public ResponseEntity<Grupo> obtenerGrupoById(@PathVariable Integer id){
		try {
			Grupo GrupoDato = servicio.obtenerGrupoById(id);
			if (GrupoDato !=null) {
			return new ResponseEntity<>(GrupoDato, HttpStatus.OK);
			} else {}return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	//@PostMapping("/Grupos")
	//public void guardarGrupo(@RequestBody Grupo datosGrupo){
	//	 servicio.guardarGrupo(datosGrupo);
	//}
	
	@PostMapping("/grupos")
	public ResponseEntity<String> guardarGrupo(@RequestBody Grupo datosGrupo){ 
		try {
			 Grupo GrupoGuardado = servicio.guardarGrupo(datosGrupo);
			 Integer idnuevo = GrupoGuardado.getId();
			 return new ResponseEntity<>("El Grupo fue almacenado " + idnuevo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al crear Grupo " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/grupos/{id}")
	public ResponseEntity<Grupo> borrarGrupoById(@PathVariable Integer id){
		try {
			Grupo GrupoExiste = servicio.obtenerGrupoById(id);
			if (GrupoExiste != null) {
				servicio.borrarGrupoById(id);
				return new ResponseEntity<>(GrupoExiste, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//servicio.borrarGrupoById(id);
	}


	@GetMapping("/gruposByName/{nombre}")
	public ResponseEntity<Grupo> obtenerGrupoById(@PathVariable String nombre){
		try {
			List<Grupo> GrupoDato = servicio.obtenerGrupoByName(nombre);
			if (!GrupoDato.isEmpty()) {
			return new ResponseEntity<>(GrupoDato.get(0), HttpStatus.OK);
			} else {}return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/grupos/{id}")
	public ResponseEntity<?> actualizarGrupo(@RequestBody Grupo datosGrupo, @PathVariable Integer id){
		try {
			Grupo GrupoExiste = servicio.obtenerGrupoById(id);
			if (GrupoExiste != null) {
				datosGrupo.setId(id);
				Grupo GrupoGuardado = servicio.guardarGrupo(datosGrupo);
				return new ResponseEntity<>(GrupoGuardado, HttpStatus.OK);
				}else {
					return new ResponseEntity<>("Grupo no existe " + id, HttpStatus.NOT_FOUND);
				}
		} catch (Exception e) {
			return new ResponseEntity<>("Error al actualizar " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
}