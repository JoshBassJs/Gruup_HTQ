package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.VentaDao;
import com.example.proyectocalid.modelo.Venta;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    
    @Autowired
    private VentaDao data;

    @Override
    public List<Venta> Listar() {
        return (List<Venta>) data.findAll();
    }

    @Override
    public Optional<Venta> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Venta v) {
        data.save(v);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public Venta Registrar(Venta p) {
        data.save(p);
        // Listado de Ventas
        List<Venta> ventas = Listar();
        // Recuperar última Venta registrado
        Venta venta = ventas.get(ventas.size() - 1);
        int idVenta = venta.getIdVenta();
        // Buscar por Id
        Optional<Venta> datoRecuperado = ListarId(idVenta);
        return datoRecuperado.get();
    }

    @Override
    public ArrayList<Venta> buscarPorFechas(String fecha1, String fecha2) {
        return data.buscarPorFechas(fecha1, fecha2);
    }

    @Override
    public ArrayList<Venta> ventasCanceladas() {
        return data.ventasCanceladas();
    }
}
