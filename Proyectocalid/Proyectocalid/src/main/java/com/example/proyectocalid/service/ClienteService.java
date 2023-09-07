package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.ClienteDao;
import com.example.proyectocalid.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    
    @Autowired
    private ClienteDao data;

    @Override
    public List<Cliente> Listar() {
        return (List<Cliente>) data.findAll();
    }

    @Override
    public Optional<Cliente> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Cliente c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public ArrayList<Cliente> clientesActivos() {
        return data.clientesActivos();
    }
}
