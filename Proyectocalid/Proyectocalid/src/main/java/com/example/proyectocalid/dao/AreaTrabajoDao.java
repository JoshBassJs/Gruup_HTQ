package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.AreaTrabajo;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AreaTrabajoDao extends CrudRepository<AreaTrabajo, Integer> {

    
    @Query(value = "SELECT * FROM area_trabajo WHERE estado = 1;", nativeQuery = true)
    ArrayList<AreaTrabajo> areasTrabajoActivos();
}
