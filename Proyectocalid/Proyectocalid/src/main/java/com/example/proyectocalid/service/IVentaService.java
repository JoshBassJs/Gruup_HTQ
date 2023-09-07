package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.Venta;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IVentaService {

    
    public List<Venta> Listar();

    public Optional<Venta> ListarId(int id);

    public void Guardar(Venta v);

    public void Eliminar(int id);

    public Venta Registrar(Venta p);

    public ArrayList<Venta> buscarPorFechas(String fecha1, String fecha2);

    public ArrayList<Venta> ventasCanceladas();
}
