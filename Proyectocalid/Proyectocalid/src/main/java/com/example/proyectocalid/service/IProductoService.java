package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    
    public List<Producto> Listar();

    public Optional<Producto> ListarId(int id);

    public void Guardar(Producto p);

    public void Eliminar(int id);

    public ArrayList<Producto> productosActivos();
}
