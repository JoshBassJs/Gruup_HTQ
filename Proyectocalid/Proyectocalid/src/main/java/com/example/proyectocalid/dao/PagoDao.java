package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.Pago;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PagoDao extends CrudRepository<Pago, Integer> {

    
    @Query(value = "SELECT * FROM pago WHERE pago.fecha BETWEEN ?1 AND ?2", nativeQuery = true)
    ArrayList<Pago> buscarPorFechas(String fecha1, String fecha2);
}
