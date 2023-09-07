package com.example.proyectocalid;

import com.example.proyectocalid.modelo.AreaTrabajo;
import com.example.proyectocalid.modelo.Colaborador;
import com.example.proyectocalid.modelo.Persona;
import com.example.proyectocalid.service.IAreaTrabajoService;
import com.example.proyectocalid.service.IColaboradorService;
import com.example.proyectocalid.service.IPersonaService;
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
public class ColaboradorController {

    
    @Autowired
    private IPersonaService dataPersona;

    @Autowired
    private IColaboradorService dataColaborador;

    @Autowired
    private IAreaTrabajoService dataAreaTrabajo;

    //LISTADO
    @GetMapping("/listadoColaborador")
    public String listarColaboradores(Model model) {
        ArrayList<Colaborador> colaboradores = (ArrayList<Colaborador>) dataColaborador.Listar();
        ArrayList<AreaTrabajo> areas = (ArrayList<AreaTrabajo>) dataAreaTrabajo.Listar();
        model.addAttribute("colaboradores", colaboradores);
        model.addAttribute("areas", areas);
        return "Colaborador/gestionColaborador";
    }

    //MOSTRAR REGISTRO
    @GetMapping("/nuevoColaborador")
    public String mostrarColaborador(Model model) {
        ArrayList<AreaTrabajo> areas = dataAreaTrabajo.areasTrabajoActivos();
        model.addAttribute("areas", areas);
        model.addAttribute("opcionEditar", 0);
        return "Colaborador/colaboradorFormulario";
    }

    //REGISTRO
    @RequestMapping(value = "/registrarColaborador", method = RequestMethod.POST)
    public String registroColaborador(@RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("areaTrabajo") AreaTrabajo area,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono,
            Model model) {

        // Crear Persona
        Persona p = new Persona();
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setEmail(email);
        p.setTelefono(telefono);
        // Registrar Persona y recuperarlo
        Persona persona = dataPersona.Registrar(p);

        // Crear Colaborador
        Colaborador c = new Colaborador();
        c.setIdPersona(persona);
        c.setIdAreaTrabajo(area);
        c.setEstado(1);
        //Registrar
        dataColaborador.Guardar(c);

        // Listar Usuarios        
        ArrayList<Colaborador> colaboradores = (ArrayList<Colaborador>) dataColaborador.Listar();
        ArrayList<AreaTrabajo> areas = (ArrayList<AreaTrabajo>) dataAreaTrabajo.Listar();
        model.addAttribute("colaboradores", colaboradores);
        model.addAttribute("areas", areas);
        return "Colaborador/gestionColaborador";
    }

    // BUSCAR COLABORADOR
    @GetMapping("/buscarColaborador")
    public String buscarColaborador(@RequestParam(value = "idColaborador") int idColaborador, Model model) {
        Optional<Colaborador> colaborador = dataColaborador.ListarId(idColaborador);
        ArrayList<AreaTrabajo> areas = dataAreaTrabajo.areasTrabajoActivos();

        model.addAttribute("areas", areas);
        model.addAttribute("colaborador", colaborador.get());
        model.addAttribute("opcionEditar", 1);
        //model.addAttribute("opcion", "actualizarUsuario");
        return "Colaborador/colaboradorFormulario";
    }

    // ACTUALIZAR
    @RequestMapping(value = "/actualizarColaborador", method = RequestMethod.POST)
    public String actualizarColaborador(@RequestParam("idColaborador") int idColaborador,
            @RequestParam("idPersona") int idPersona,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono,
            @RequestParam("areaTrabajo") AreaTrabajo area,
            @RequestParam("estado") int estado,
            Model model) {

        // Crear Persona
        Persona p = new Persona();
        p.setIdPersona(idPersona);
        p.setApellido(apellido);
        p.setEmail(email);
        p.setNombre(nombre);
        p.setTelefono(telefono);
        dataPersona.Guardar(p);

        //Crear Colaborador
        Colaborador c = new Colaborador();
        c.setIdColaborador(idColaborador);
        c.setEstado(estado);
        c.setIdAreaTrabajo(area);
        c.setIdPersona(dataPersona.ListarId(idPersona).get());
        dataColaborador.Guardar(c);

        //Listar
        ArrayList<Colaborador> colaboradores = (ArrayList<Colaborador>) dataColaborador.Listar();
        ArrayList<AreaTrabajo> areas = (ArrayList<AreaTrabajo>) dataAreaTrabajo.Listar();
        model.addAttribute("colaboradores", colaboradores);
        model.addAttribute("areas", areas);
        return "Colaborador/gestionColaborador";
    }
}
