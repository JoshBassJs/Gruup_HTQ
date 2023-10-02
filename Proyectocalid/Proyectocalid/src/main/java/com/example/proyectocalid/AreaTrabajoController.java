package com.example.proyectocalid;

import com.example.proyectocalid.modelo.AreaTrabajo;
import com.example.proyectocalid.modelo.Colaborador;
import com.example.proyectocalid.modelo.Persona;
import com.example.proyectocalid.service.IAreaTrabajoService;
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
public class AreaTrabajoController {

    //para brobar.
    @Autowired
    private IAreaTrabajoService dataAreaTrabajo;

    //LISTADO
    @GetMapping("/listadoAreaTrabajo")
    public String listarAreaTrabajo(Model model) {
        ArrayList<AreaTrabajo> areas = (ArrayList<AreaTrabajo>) dataAreaTrabajo.Listar();
        model.addAttribute("areas", areas);
        return "AreaTrabajo/listadoAreaTrabajo";
    }

    //MOSTRAR REGISTRO
    @GetMapping("/nuevoAreaTrabajo")
    public String mostrarAreaTrabajo(Model model) {
        model.addAttribute("opcionEditar", 0);
        return "AreaTrabajo/areaTrabajoFormulario";
    }

    //REGISTRO
    @RequestMapping(value = "/registrarAreaTrabajo", method = RequestMethod.POST)
    public String registroAreaTrabajo(@RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            Model model) {

        // Crear Area de Trabajo
        AreaTrabajo a = new AreaTrabajo();
        a.setNombre(nombre);
        a.setDescripcion(descripcion);
        a.setEstado(1);
        dataAreaTrabajo.Guardar(a);

        ArrayList<AreaTrabajo> areas = (ArrayList<AreaTrabajo>) dataAreaTrabajo.Listar();
        model.addAttribute("areas", areas);
        return "AreaTrabajo/listadoAreaTrabajo";
    }

    // BUSCAR ÁREA DE TRABAJO
    @GetMapping("/buscarAreaTrabajo")
    public String buscarAreaTrabajo(@RequestParam(value = "idAreaTrabajo") int idAreaTrabajo, Model model) {
        Optional<AreaTrabajo> area = dataAreaTrabajo.ListarId(idAreaTrabajo);

        model.addAttribute("area", area.get());
        model.addAttribute("opcionEditar", 1);
        return "AreaTrabajo/areaTrabajoFormulario";
    }

    // ACTUALIZAR
    @RequestMapping(value = "/actualizarAreaTrabajo", method = RequestMethod.POST)
    public String actualizarAreaTrabajo(@RequestParam("idAreaTrabajo") int idAreaTrabajo,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("estado") int estado,
            Model model) {

        //Crear Area de trabajo
        AreaTrabajo a = new AreaTrabajo();
        a.setIdAreaTrabajo(idAreaTrabajo);
        a.setNombre(nombre);
        a.setDescripcion(descripcion);
        a.setEstado(estado);
        dataAreaTrabajo.Guardar(a);

        //Listar
        ArrayList<AreaTrabajo> areas = (ArrayList<AreaTrabajo>) dataAreaTrabajo.Listar();
        model.addAttribute("areas", areas);
        return "AreaTrabajo/listadoAreaTrabajo";
    }

}
