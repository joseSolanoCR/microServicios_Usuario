package com.academia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.model.Usuario;
import com.academia.repository.UsuarioRepositorio;

@Service
public class UsuarioServicio {
	@Autowired
	private UsuarioRepositorio repositorio;
	

	public List<Usuario> listarGrupo(){
		return repositorio.findAll();
		
	}
	
	public Usuario obtenerGrupoById(Integer id){
		Optional<Usuario> GrupoOptional = repositorio.findById(id);
		
		if(GrupoOptional.isPresent()) {
			return GrupoOptional.get();
		} else {
			return null;
		}
		//return repositorio.findById(id).get();	
	}
	
	//public void guardarGrupo(Usuario datosGrupo) {
	//	repositorio.save(datosGrupo);
	//}
	public Usuario guardarGrupo(Usuario datosGrupo) {
		return repositorio.save(datosGrupo);
	}

	public List<Usuario> obtenerGrupoByName(String descripcion){
		return repositorio.findByNombreEquals(descripcion);
	}
	
	public void borrarGrupoById(Integer id){
		 repositorio.deleteById(id);
	}
}