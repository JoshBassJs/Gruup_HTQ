package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.ProductoDao;
import com.example.proyectocalid.modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    
    @Autowired
    private ProductoDao data;

    @Override
    public List<Producto> Listar() {
        return (List<Producto>) data.findAll();
    }

    @Override
    public Optional<Producto> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Producto p) {
        data.save(p);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public ArrayList<Producto> productosActivos() {
        return data.productosActivos();
    }
}
