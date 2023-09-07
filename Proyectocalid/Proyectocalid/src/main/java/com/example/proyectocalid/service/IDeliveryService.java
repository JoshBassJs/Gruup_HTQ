package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.Delivery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IDeliveryService {

    
    public List<Delivery> Listar(); //Mostrar todo

    public Optional<Delivery> ListarId(int id); //Mostrar por parametro

    public void Guardar(Delivery d); //Guardar

    public void Eliminar(int id);
    
    public ArrayList<Delivery> buscarDeliveriesDeCliente(int idCliente);
}
