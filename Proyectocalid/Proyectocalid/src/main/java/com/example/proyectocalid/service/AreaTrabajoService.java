package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.AreaTrabajoDao;
import com.example.proyectocalid.modelo.AreaTrabajo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaTrabajoService implements IAreaTrabajoService {

    
    @Autowired
    private AreaTrabajoDao data;

    @Override
    public List<AreaTrabajo> Listar() {
        return (List<AreaTrabajo>) data.findAll();
    }

    @Override
    public Optional<AreaTrabajo> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(AreaTrabajo a) {
        data.save(a);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public ArrayList<AreaTrabajo> areasTrabajoActivos() {
        return data.areasTrabajoActivos();
    }
}
