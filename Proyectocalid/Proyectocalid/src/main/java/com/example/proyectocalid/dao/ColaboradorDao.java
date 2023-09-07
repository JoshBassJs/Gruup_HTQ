package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.Colaborador;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ColaboradorDao extends CrudRepository<Colaborador, Integer> {

    
    @Query(value = "SELECT * FROM colaborador WHERE estado = 1", nativeQuery = true)
    ArrayList<Colaborador> colaboradoresActivos();
}
