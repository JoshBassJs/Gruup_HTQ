package com.example.proyectocalid.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "VENTA_DETALLE")
public class VentaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVentaDetalle;
    @ManyToOne()
    @JoinColumn(name = "ID_VENTA")
    private Venta idVenta;
    @ManyToOne()
    @JoinColumn(name = "ID_PRODUCTO")
    private Producto idProducto;
    @Column(name = "CANTIDAD")
    public int cantidad;
    @Column(name = "PRECIO")
    public Double precio;
    @Column(name = "TOTAL")
    public Double total;
}
