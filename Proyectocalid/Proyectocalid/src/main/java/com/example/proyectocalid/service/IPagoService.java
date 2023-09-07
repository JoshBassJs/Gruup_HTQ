package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.Pago;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IPagoService {

    
    public List<Pago> Listar(); //Mostrar todo

    public Optional<Pago> ListarId(int id); //Mostrar por parametro

    public void Guardar(Pago p); //Guardar

    public void Eliminar(int id);

    public ArrayList<Pago> buscarPorFechas(String fecha1, String fecha2);

    public Double obtenerMontoTotal(ArrayList<Pago> pagos);
}
