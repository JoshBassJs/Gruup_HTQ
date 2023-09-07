package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.Delivery;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryDao extends CrudRepository<Delivery, Integer> {

    
    @Query(value = "SELECT delivery.id_delivery, delivery.direccion, delivery.numero_celular, delivery.id_venta, delivery.estado FROM delivery INNER JOIN venta on delivery.id_venta = venta.id_venta WHERE venta.id_cliente LIKE ?1", nativeQuery = true)
    ArrayList<Delivery> buscarDeliveriesDeCliente(int idCliente);
}
