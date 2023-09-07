package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.Venta;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VentaDao extends CrudRepository<Venta, Integer> {

    @Query(value = "SELECT * FROM venta WHERE venta.fecha BETWEEN ?1 AND ?2 AND venta.cancelado LIKE 1", nativeQuery = true)
    ArrayList<Venta> buscarPorFechas(String fecha1, String fecha2);
    
    
    @Query(value = "SELECT * from venta WHERE venta.cancelado LIKE 1", nativeQuery = true)
    ArrayList<Venta> ventasCanceladas();
}
