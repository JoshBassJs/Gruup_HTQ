package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.Carrito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ICarritoService {

    
    public List<Carrito> Listar(); //Mostrar todo

    public Optional<Carrito> ListarId(int id); //Mostrar por parametro

    public void Guardar(Carrito c); //Guardar

    public void Eliminar(int id);

    public Carrito buscarCarritoDeCliente(int idCliente);
}
