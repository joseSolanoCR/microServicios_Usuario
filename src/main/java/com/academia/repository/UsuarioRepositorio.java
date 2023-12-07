package com.academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
	List<Usuario> findByNombreEquals(String nombre);
}
