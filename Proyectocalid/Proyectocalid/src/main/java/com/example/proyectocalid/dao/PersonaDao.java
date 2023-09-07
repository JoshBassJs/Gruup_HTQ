package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaDao extends CrudRepository<Persona, Integer> {

    
}
