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
@Table(name = "CARRITO")
public class Carrito implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCarrito;
    @ManyToOne()
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente idCliente;
    @ManyToOne()
    @JoinColumn(name = "ID_VENTA")
    public Venta idVenta;
    @Column(name = "CANCELADO")
    public int cancelado;
}
