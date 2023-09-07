package com.example.proyectocalid.service;

import com.example.proyectocalid.dao.PagoDao;
import com.example.proyectocalid.modelo.Pago;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService implements IPagoService {

    
    @Autowired
    private PagoDao data;

    @Override
    public List<Pago> Listar() {
        return (List<Pago>) data.findAll();
    }

    @Override
    public Optional<Pago> ListarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Pago p) {
        data.save(p);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public ArrayList<Pago> buscarPorFechas(String fecha1, String fecha2) {
        return data.buscarPorFechas(fecha1, fecha2);
    }

    @Override
    public Double obtenerMontoTotal(ArrayList<Pago> pagos) {
        Double montoTotal = 0.0;

        for (Pago p : pagos) {
            montoTotal += p.idVenta.getPagoTotal();
        }
        return montoTotal;
    }
}
