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
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Column(name = "CLAVE")
    private String clave;
    @ManyToOne()
    @JoinColumn(name = "ID_PERSONA")
    private Persona idPersona;
    @ManyToOne()
    @JoinColumn(name = "ID_TIPO_USUARIO")
    private TipoUsuario idTipoUsuario;
    @Column(name="ESTADO")
    public int estado;

    
}
