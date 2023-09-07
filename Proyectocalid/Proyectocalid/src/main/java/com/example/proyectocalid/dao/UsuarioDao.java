package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.Usuario;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
    
    @Query(value = "SELECT * FROM usuario WHERE estado = 1", nativeQuery = true)
    ArrayList<Usuario> usuariosActivos();

}
