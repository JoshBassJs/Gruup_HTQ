package com.example.proyectocalid;

import com.example.proyectocalid.modelo.Cliente;
import com.example.proyectocalid.modelo.Persona;
import com.example.proyectocalid.service.IClienteService;
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
public class ClienteController {

    
    @Autowired
    private IPersonaService dataPersona;

    @Autowired
    private IClienteService dataCliente;

    //LISTADO
    @GetMapping("/listadoCliente")
    public String listarClientes(Model model) {
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) dataCliente.Listar();
        model.addAttribute("clientes", clientes);
        return "Cliente/gestionCliente";
    }

    //MOSTRAR REGISTRO
    @GetMapping("/nuevoCliente")
    public String mostrarCliente(Model model) {
        model.addAttribute("opcionEditar", 0);
        return "Cliente/clienteFormulario";
    }

    //REGISTRAR
    @RequestMapping(value = "/registrarCliente", method = RequestMethod.POST)
    public String registrarCliente(@RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono,
            @RequestParam("nombreUsuario") String nombreUsuario,
            @RequestParam("clave") String clave,
            Model model) {

        // Crear Persona
        Persona p = new Persona();
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setEmail(email);
        p.setTelefono(telefono);
        // Registrar Persona y recuperarlo
        Persona persona = dataPersona.Registrar(p);

        // Crear Cliente
        Cliente c = new Cliente();
        c.setIdPersona(persona);
        c.setNombreUsuario(nombreUsuario);
        c.setClave(clave);
        c.setEstado(1);

        // Registrar Cliente
        dataCliente.Guardar(c);

        // Listar Clientes        
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) dataCliente.Listar();
        model.addAttribute("clientes", clientes);
        return "Cliente/gestionCliente";
    }

    // BUSCAR CLIENTE
    @GetMapping("/buscarCliente")
    public String buscarCliente(@RequestParam(value = "idCliente") int idCliente, Model model) {
        Optional<Cliente> cliente = dataCliente.ListarId(idCliente);

        model.addAttribute("cliente", cliente.get());
        model.addAttribute("opcionEditar", 1);
        return "Cliente/clienteFormulario";
    }

    // ACTUALIZAR
    @RequestMapping(value = "/actualizarCliente", method = RequestMethod.POST)
    public String actualizarCliente(@RequestParam("idCliente") int idCliente,
            @RequestParam("idPersona") int idPersona,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono,
            @RequestParam("nombreUsuario") String nombreUsuario,
            @RequestParam("clave") String clave,
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

        //Crear Cliente
        Cliente c = new Cliente();
        c.setIdCliente(idCliente);
        c.setNombreUsuario(nombreUsuario);
        c.setClave(clave);
        c.setEstado(estado);
        c.setIdPersona(dataPersona.ListarId(idPersona).get());

        //Guardar
        dataCliente.Guardar(c);

        //Listar
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) dataCliente.Listar();
        model.addAttribute("clientes", clientes);
        return "Cliente/gestionCliente";
    }
}
