package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    
    public List<Usuario> Listar(); //Mostrar todo

    public Optional<Usuario> ListarId(int id); //Mostrar por parametro

    public void Guardar(Usuario u); //Guardar

    public void Eliminar(int id);

    public ArrayList<Usuario> usuariosActivos();

}
