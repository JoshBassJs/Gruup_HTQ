package com.example.proyectocalid.service;

import com.example.proyectocalid.modelo.VentaDetalle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IVentaDetalleService {

    
    public List<VentaDetalle> Listar();

    public Optional<VentaDetalle> ListarId(int id);

    public void Guardar(VentaDetalle vd);

    public void Eliminar(int id);

    public VentaDetalle Registrar(VentaDetalle pd);

    public ArrayList<VentaDetalle> buscarPoridVenta(int idVenta);

    public Double obtenerMontoTotal(int idVenta);

    public void eliminarPorIdVenta(int idVenta);

    public ArrayList<VentaDetalle> buscarListadoDetalleVenta(int idVenta);

    public Double obtenerMontoTotalVenta(int idVenta);

}
