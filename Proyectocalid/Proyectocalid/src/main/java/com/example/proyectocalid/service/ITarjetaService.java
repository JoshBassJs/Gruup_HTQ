package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.Tarjeta;
import java.util.List;
import java.util.Optional;

public interface ITarjetaService {

    
    public List<Tarjeta> Listar(); //Mostrar todo

    public Optional<Tarjeta> ListarId(int id); //Mostrar por parametro

    public void Guardar(Tarjeta t); //Guardar

    public void Eliminar(int id);

    public Tarjeta Registrar(Tarjeta t);
}
