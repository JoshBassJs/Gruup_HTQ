package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.TarjetaDao;
import com.example.proyectocalid.modelo.Tarjeta;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarjetaService implements ITarjetaService {

    
    @Autowired
    private TarjetaDao data;

    @Override
    public List<Tarjeta> Listar() {
        return (List<Tarjeta>) data.findAll();
    }

    @Override
    public Optional<Tarjeta> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Tarjeta t) {
        data.save(t);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public Tarjeta Registrar(Tarjeta t) {
        data.save(t);
        // Listado de Tarjetas
        List<Tarjeta> tarjetas = Listar();
        // Recuperar última tarjeta registrada
        Tarjeta tarjeta = tarjetas.get(tarjetas.size() - 1);
        int idTarjeta = tarjeta.getIdTarjeta();
        // Buscar por Id
        Optional<Tarjeta> datoRecuperado = ListarId(idTarjeta);
        return datoRecuperado.get();
    }
}
