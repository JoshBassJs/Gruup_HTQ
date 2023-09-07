package com.example.proyectocalid;

import com.example.proyectocalid.modelo.Cliente;
import com.example.proyectocalid.modelo.Delivery;
import com.example.proyectocalid.service.IDeliveryService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeliveryController {

    @Autowired
    private IDeliveryService dataDelivery;

    // LISTADO DE DELIVERY
    @GetMapping("/listadoDeliverys")
    public String listadoDeliverys(Model model) {
        ArrayList<Delivery> deliverys = (ArrayList<Delivery>) dataDelivery.Listar();
        model.addAttribute("deliverys", deliverys);
        return "Delivery/listadoDelivery";
    }

    // BUSCAR DELIVERY
    @GetMapping("/buscarDelivery")
    public String buscarDelivery(@RequestParam(value = "idDelivery") int idDelivery, Model model) {
        Optional<Delivery> delivery = dataDelivery.ListarId(idDelivery);

        model.addAttribute("delivery", delivery.get());
        return "Delivery/editarDelivery";
    }

    // ACTUALIZAR
    @RequestMapping(value = "/actualizarDelivery", method = RequestMethod.POST)
    public String actualizarDelivery(@RequestParam("idDelivery") int idDelivery,
            @RequestParam("estado") String estado,
            Model model) {
        
        Optional<Delivery> delivery = dataDelivery.ListarId(idDelivery);
        delivery.get().setEstado(estado);
        //Guardar
        dataDelivery.Guardar(delivery.get());
        return listadoDeliverys(model);
    }
}
