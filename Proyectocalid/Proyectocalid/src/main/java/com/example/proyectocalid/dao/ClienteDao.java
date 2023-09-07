package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.Cliente;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {

    
    @Query(value = "SELECT * FROM cliente WHERE estado = 1", nativeQuery = true)
    ArrayList<Cliente> clientesActivos();
}
