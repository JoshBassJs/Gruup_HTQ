package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.ColaboradorDao;
import com.example.proyectocalid.modelo.Colaborador;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService implements IColaboradorService {

    
    @Autowired
    private ColaboradorDao data;

    @Override
    public List<Colaborador> Listar() {
        return (List<Colaborador>) data.findAll();
    }

    @Override
    public Optional<Colaborador> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Colaborador c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public ArrayList<Colaborador> colaboradoresActivos() {
        return data.colaboradoresActivos();
    }
}
