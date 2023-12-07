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

import com.academia.model.Usuario;
import com.academia.service.UsuarioServicio;

@RestController
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio servicio;

	@GetMapping("/usuarios")
	public List<Usuario> listarGrupo(){
		return servicio.listarGrupo();
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> obtenerGrupoById(@PathVariable Integer id){
		try {
			Usuario GrupoDato = servicio.obtenerGrupoById(id);
			if (GrupoDato !=null) {
			return new ResponseEntity<>(GrupoDato, HttpStatus.OK);
			} else {}return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	//@PostMapping("/Grupos")
	//public void guardarGrupo(@RequestBody Usuario datosGrupo){
	//	 servicio.guardarGrupo(datosGrupo);
	//}
	
	@PostMapping("/usuarios")
	public ResponseEntity<String> guardarGrupo(@RequestBody Usuario datosGrupo){ 
		try {
			 Usuario GrupoGuardado = servicio.guardarGrupo(datosGrupo);
			 Integer idnuevo = GrupoGuardado.getId();
			 return new ResponseEntity<>("El Usuario fue almacenado " + idnuevo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al crear Usuario " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> borrarGrupoById(@PathVariable Integer id){
		try {
			Usuario GrupoExiste = servicio.obtenerGrupoById(id);
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


	@GetMapping("/usuarioByName/{nombre}")
	public ResponseEntity<Usuario> obtenerGrupoById(@PathVariable String nombre){
		try {
			List<Usuario> GrupoDato = servicio.obtenerGrupoByName(nombre);
			if (!GrupoDato.isEmpty()) {
			return new ResponseEntity<>(GrupoDato.get(0), HttpStatus.OK);
			} else {}return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> actualizarGrupo(@RequestBody Usuario datosGrupo, @PathVariable Integer id){
		try {
			Usuario GrupoExiste = servicio.obtenerGrupoById(id);
			if (GrupoExiste != null) {
				datosGrupo.setId(id);
				Usuario GrupoGuardado = servicio.guardarGrupo(datosGrupo);
				return new ResponseEntity<>(GrupoGuardado, HttpStatus.OK);
				}else {
					return new ResponseEntity<>("Usuario no existe " + id, HttpStatus.NOT_FOUND);
				}
		} catch (Exception e) {
			return new ResponseEntity<>("Error al actualizar " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
}