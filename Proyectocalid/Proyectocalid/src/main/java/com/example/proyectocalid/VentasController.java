
package com.example.proyectocalid;

import com.example.proyectocalid.modelo.Venta;
import com.example.proyectocalid.modelo.VentaDetalle;
import com.example.proyectocalid.service.IVentaDetalleService;
import com.example.proyectocalid.service.IVentaService;
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
public class VentasController {
    
    @Autowired
    private IVentaService dataVenta;

    @Autowired
    private IVentaDetalleService dataVentaDetalle;
    
    
    //LISTADO DE VENTAS
    //LISTADO
    @GetMapping("/listadoVentas")
    public String listarColaboradores(Model model) {
        ArrayList<Venta> ventas = (ArrayList<Venta>) dataVenta.ventasCanceladas();
        model.addAttribute("ventas", ventas);
        return "Venta/listadoVentas";
    }
    
    //FILTRAR VENTA POR FECHA
    @RequestMapping(value = "/filtrarVentaFecha", method = RequestMethod.POST)
    public String filtrarVentaFecha(@RequestParam("fecha1") String fecha1,
            @RequestParam("fecha2") String fecha2,
            Model model) throws ParseException {
        ArrayList<Venta> ventas = dataVenta.buscarPorFechas(fecha1.replace("-", "/"), fecha2.replace("-", "/"));
        //ArrayList<Venta> ventas = dataVenta.buscarPorFechas("2022/06/20", "2022/06/27");
        model.addAttribute("ventas", ventas);
        return "Venta/listadoVentas";
    }
    
    //DETALLE DE VENTAS
    @GetMapping("/buscarDetalleVenta")
    public String buscarDetalleVenta(@RequestParam(value = "idVenta") int idVenta,
            @RequestParam(value = "inicio") int inicio,
            Model model) {
        
        ArrayList<VentaDetalle> filtroVentas = dataVentaDetalle.buscarListadoDetalleVenta(idVenta);
        model.addAttribute("filtroVentas", filtroVentas);
        model.addAttribute("montoTotal", dataVentaDetalle.obtenerMontoTotalVenta(idVenta));
        model.addAttribute("botonVenta", inicio);
        return "Venta/detalleVenta";
    }
}
