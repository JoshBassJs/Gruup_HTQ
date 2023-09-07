package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.Carrito;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarritoDao extends CrudRepository<Carrito, Integer> {

    
    @Query(value = "SELECT * FROM `carrito` WHERE carrito.cancelado LIKE '0' AND carrito.id_cliente LIKE ?1", nativeQuery = true)
    Carrito buscarCarritoDeCliente(int idCliente);
}
