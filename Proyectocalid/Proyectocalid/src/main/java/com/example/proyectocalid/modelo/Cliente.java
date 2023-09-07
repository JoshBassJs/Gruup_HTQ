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
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    @ManyToOne()
    @JoinColumn(name = "ID_PERSONA")
    private Persona idPersona;
    @Column(name = "ESTADO")
    public int estado;
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Column(name = "CLAVE")
    private String clave;
}
