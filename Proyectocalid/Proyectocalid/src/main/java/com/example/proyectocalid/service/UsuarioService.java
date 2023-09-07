package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.UsuarioDao;
import com.example.proyectocalid.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    
    @Autowired
    private UsuarioDao data;

    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) data.findAll();
    }

    @Override
    public Optional<Usuario> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Usuario u) {
        data.save(u);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public ArrayList<Usuario> usuariosActivos() {
        return data.usuariosActivos();
    }

}
