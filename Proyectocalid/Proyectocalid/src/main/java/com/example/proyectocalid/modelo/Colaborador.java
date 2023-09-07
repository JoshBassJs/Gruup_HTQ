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
@Table(name = "COLABORADOR")
public class Colaborador implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idColaborador;
    @ManyToOne()
    @JoinColumn(name = "ID_PERSONA")
    private Persona idPersona;
    @ManyToOne()
    @JoinColumn(name = "ID_AREA_TRABAJO")
    private AreaTrabajo idAreaTrabajo;
    @Column(name = "ESTADO")
    public int estado;
}
