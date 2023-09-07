package com.example.proyectocalid.dao;

import com.example.proyectocalid.modelo.VentaDetalle;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VentaDetalleDao extends CrudRepository<VentaDetalle, Integer> {

    ///MODIFICAR
    //@Query(value = "select * from venta_detalle INNER JOIN mesa on pedido_detalle.id_pedido = mesa.id_pedido WHERE pedido_detalle.id_pedido LIKE %?1%", nativeQuery = true)
    @Query(value = "select * from venta_detalle INNER JOIN venta ON venta_detalle.id_venta = venta.id_venta WHERE venta_detalle.id_venta LIKE %?1%", nativeQuery = true)
    ArrayList<VentaDetalle> buscarPoridVenta(int idVenta);

    
    @Query(value = "SELECT * FROM venta_detalle WHERE venta_detalle.id_venta LIKE %?1%", nativeQuery = true)
    ArrayList<VentaDetalle> buscarListadoDetalleVenta(int idVenta);

}
