
package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.DeliveryDao;
import com.example.proyectocalid.modelo.Delivery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService implements IDeliveryService{
    
    
    @Autowired
    private DeliveryDao data;

    @Override
    public List<Delivery> Listar() {
        return (List<Delivery>) data.findAll();
    }

    @Override
    public Optional<Delivery> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Delivery d) {
        data.save(d);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public ArrayList<Delivery> buscarDeliveriesDeCliente(int idCliente) {
        return data.buscarDeliveriesDeCliente(idCliente);
    }
}
