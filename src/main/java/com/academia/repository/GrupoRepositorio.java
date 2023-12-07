package com.academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.model.Grupo;

public interface GrupoRepositorio extends JpaRepository<Grupo, Integer>{
	List<Grupo> findByNombreEquals(String nombre);
}
