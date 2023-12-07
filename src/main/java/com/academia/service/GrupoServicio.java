package com.academia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.model.Grupo;
import com.academia.repository.GrupoRepositorio;

@Service
public class GrupoServicio {
	@Autowired
	private GrupoRepositorio repositorio;
	

	public List<Grupo> listarGrupo(){
		return repositorio.findAll();
		
	}
	
	public Grupo obtenerGrupoById(Integer id){
		Optional<Grupo> GrupoOptional = repositorio.findById(id);
		
		if(GrupoOptional.isPresent()) {
			return GrupoOptional.get();
		} else {
			return null;
		}
		//return repositorio.findById(id).get();	
	}
	
	//public void guardarGrupo(Grupo datosGrupo) {
	//	repositorio.save(datosGrupo);
	//}
	public Grupo guardarGrupo(Grupo datosGrupo) {
		return repositorio.save(datosGrupo);
	}

	public List<Grupo> obtenerGrupoByName(String descripcion){
		return repositorio.findByNombreEquals(descripcion);
	}
	
	public void borrarGrupoById(Integer id){
		 repositorio.deleteById(id);
	}
}