package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.VentaDetalleDao;
import com.example.proyectocalid.modelo.VentaDetalle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaDetalleService implements IVentaDetalleService {

    
    @Autowired
    private VentaDetalleDao data;

    @Override
    public List<VentaDetalle> Listar() {
        return (List<VentaDetalle>) data.findAll();
    }

    @Override
    public Optional<VentaDetalle> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(VentaDetalle vd) {
        data.save(vd);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public VentaDetalle Registrar(VentaDetalle pd) {
        data.save(pd);
        // Listado de VentaDetalle
        List<VentaDetalle> ventaDetalles = Listar();
        // Recuperar último VentaDetalle registrado
        VentaDetalle ventaDetalle = ventaDetalles.get(ventaDetalles.size() - 1);
        int idVentaDetalle = ventaDetalle.getIdVentaDetalle();
        // Buscar por Id
        Optional<VentaDetalle> datoRecuperado = ListarId(idVentaDetalle);
        return datoRecuperado.get();
    }

    @Override
    public ArrayList<VentaDetalle> buscarPoridVenta(int idVenta) {
        return data.buscarPoridVenta(idVenta);
    }

    @Override
    public Double obtenerMontoTotal(int idVenta) {
        ArrayList<VentaDetalle> ventaDetalle = buscarPoridVenta(idVenta);
        Double montoTotal = 0.0;
        for (VentaDetalle vd : ventaDetalle) {
            montoTotal += vd.getTotal();
        }
        return montoTotal;
    }

    @Override
    public void eliminarPorIdVenta(int idVenta) {
        ArrayList<VentaDetalle> ventaDetalles = (ArrayList<VentaDetalle>) Listar();

        for (VentaDetalle vd : ventaDetalles) {
            if (vd.getIdVenta().getIdVenta() == idVenta) {
                Eliminar(vd.getIdVentaDetalle());
            }
        }
    }

    @Override
    public ArrayList<VentaDetalle> buscarListadoDetalleVenta(int idVenta) {
        return data.buscarListadoDetalleVenta(idVenta);
    }

    @Override
    public Double obtenerMontoTotalVenta(int idVenta) {
        ArrayList<VentaDetalle> ventaDetalle = buscarListadoDetalleVenta(idVenta);
        Double montoTotal = 0.0;
        for (VentaDetalle vd : ventaDetalle) {
            montoTotal += vd.getTotal();
        }
        return montoTotal;
    }
}
