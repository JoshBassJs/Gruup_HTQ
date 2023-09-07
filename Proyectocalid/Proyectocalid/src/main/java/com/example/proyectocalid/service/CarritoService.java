package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.CarritoDao;
import com.example.proyectocalid.modelo.Carrito;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoService implements ICarritoService {

    
    @Autowired
    private CarritoDao data;

    @Override
    public List<Carrito> Listar() {
        return (List<Carrito>) data.findAll();
    }

    @Override
    public Optional<Carrito> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Carrito c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public Carrito buscarCarritoDeCliente(int idCliente) {
        return data.buscarCarritoDeCliente(idCliente);
    }
}
