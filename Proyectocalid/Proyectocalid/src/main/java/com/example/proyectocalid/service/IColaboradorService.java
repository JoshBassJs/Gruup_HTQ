package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.Colaborador;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IColaboradorService {

    
    public List<Colaborador> Listar(); //Mostrar todo

    public Optional<Colaborador> ListarId(int id); //Mostrar por parametro

    public void Guardar(Colaborador c); //Guardar

    public void Eliminar(int id);

    public ArrayList<Colaborador> colaboradoresActivos();

}
