package com.example.proyectocalid.modelo;

import java.io.File;
import java.io.Serializable;
import java.sql.Blob;
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
@Table(name = "PRODUCTO")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;
    @ManyToOne()
    @JoinColumn(name = "ID_TIPO_PRODUCTO")
    private TipoProducto idTipoProducto;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PRECIO")
    public double precio;
    @Column(name = "DESCRIPCION")
    public String descripcion;
    @Column(name = "IMAGEN")
    private String imagen;
    @Column(name = "ESTADO")
    public int estado;
}
