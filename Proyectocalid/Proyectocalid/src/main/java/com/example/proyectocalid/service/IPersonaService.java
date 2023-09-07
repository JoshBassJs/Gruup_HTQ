package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.Persona;
import java.util.List;
import java.util.Optional;

public interface IPersonaService {

    
    public List<Persona> Listar(); //Mostrar todo

    public Optional<Persona> ListarId(int id); //Mostrar por parametro

    public void Guardar(Persona p); //Guardar

    public void Eliminar(int id);

    public Persona Registrar(Persona p);

}
