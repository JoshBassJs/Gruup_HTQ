package com.example.proyectocalid;

import com.example.proyectocalid.modelo.Pago;
import com.example.proyectocalid.service.IPagoService;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagosController {

    @Autowired
    private IPagoService dataPago;

    
    //LISTADO DE PAGOS
    @GetMapping("/listadoPagos")
    public String listadoPagos(Model model) {
        ArrayList<Pago> pagos = (ArrayList<Pago>) dataPago.Listar();
        Double montoTotal = dataPago.obtenerMontoTotal(pagos);
        //DecimalFormat formato = new DecimalFormat("#.00");
        model.addAttribute("pagos", pagos);
        //model.addAttribute("montoTotal", formato.format(montoTotal));
        model.addAttribute("montoTotal", montoTotal);
        return "Venta/listadoPagos";
    }

    //FILTRAR PAGOS POR FECHA
    @RequestMapping(value = "/filtrarPagoFecha", method = RequestMethod.POST)
    public String filtrarPagoFecha(@RequestParam("fecha1") String fecha1,
            @RequestParam("fecha2") String fecha2,
            Model model) throws ParseException {
        ArrayList<Pago> pagos = dataPago.buscarPorFechas(fecha1.replace("-", "/"), fecha2.replace("-", "/"));
        Double montoTotal = dataPago.obtenerMontoTotal(pagos);
        DecimalFormat formato = new DecimalFormat("#.00");
        model.addAttribute("pagos", pagos);
        model.addAttribute("montoTotal", montoTotal);
        return "Venta/listadoPagos";
    }
}
