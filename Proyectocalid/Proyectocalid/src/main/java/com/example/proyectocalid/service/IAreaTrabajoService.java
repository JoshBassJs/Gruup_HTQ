package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.AreaTrabajo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IAreaTrabajoService {

    
    public List<AreaTrabajo> Listar(); //Mostrar todo

    public Optional<AreaTrabajo> ListarId(int id); //Mostrar por parametro

    public void Guardar(AreaTrabajo a); //Guardar

    public void Eliminar(int id);

    public ArrayList<AreaTrabajo> areasTrabajoActivos();

}
