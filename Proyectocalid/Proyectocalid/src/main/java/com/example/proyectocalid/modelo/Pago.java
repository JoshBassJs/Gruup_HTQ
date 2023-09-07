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
@Table(name = "PAGO")
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idPago;
    @Column(name = "FECHA")
    public String fecha;
    @ManyToOne()
    @JoinColumn(name = "ID_VENTA")
    public Venta idVenta;
    @ManyToOne()
    @JoinColumn(name = "ID_TARJETA")
    public Tarjeta idTarjeta;
}
