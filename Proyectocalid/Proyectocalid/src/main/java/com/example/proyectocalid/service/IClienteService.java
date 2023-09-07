package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IClienteService {

    
    public List<Cliente> Listar(); //Mostrar todo

    public Optional<Cliente> ListarId(int id); //Mostrar por parametro

    public void Guardar(Cliente c); //Guardar

    public void Eliminar(int id);

    public ArrayList<Cliente> clientesActivos();
}
