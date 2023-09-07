package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.Producto;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDao extends CrudRepository<Producto, Integer> {

    
    @Query(value = "SELECT * FROM producto WHERE estado = 1", nativeQuery = true)
    ArrayList<Producto> productosActivos();
}
