package com.example.proyectocalid;

import com.example.proyectocalid.modelo.*;
import com.example.proyectocalid.service.IPersonaService;
import com.example.proyectocalid.service.IProductoService;
import com.example.proyectocalid.service.IUsuarioService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Controlador {

    private ArrayList<Usuario> users;
    private ArrayList<Producto> product;
    private Usuario log;

    
    @Autowired
    private IPersonaService dataPersona;
    @Autowired
    private IUsuarioService dataUsuario;
    @Autowired
    private IProductoService dataProducto;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("aux", 0);
        return "login";
    }

    Usuario userLog = new Usuario();

    private void leerUsuarios() {

        Usuario user1 = new Usuario();
        user1.setNombreUsuario("admin");
        user1.setClave("123");
        this.userLog = user1;
    }

    @RequestMapping(value = "/Ingresar", method = RequestMethod.POST)
    public String loguearse(@RequestParam("user") String usr, @RequestParam("pass") String pss, Model model) {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) dataUsuario.usuariosActivos();
        /*for (Usuario user : usuarios) {
            if (user.getNombreUsuario().equals(usr) && user.getClave().equals(pss)) {
                return "index";
            }
        }*/
        if (usr.equals("admin") && pss.equals("123")) {
            log = userLog;
            return "index";
        }
        return "login";
    }

    @GetMapping("/Ingresar")
    public String ingresarPermiso(Model model) {
        if (log != null) {
            model.addAttribute("usuario", log);
            return "index";
        } else {
            return "login";
        }
    }

    @GetMapping("/Logout")
    public String salir() {
        log = null;
        return "login";
    }

    @GetMapping("/Gestionar-Productos")
    public String gestionProductos(Model model) {
        if (log != null) {
            model.addAttribute("productos", product);
            return "index";
        } else {
            return "login";
        }
    }

    ///MODULO DE USUARIOS    
    @GetMapping("/listadoUsuario")
    public String listarServicios(Model model) {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) dataUsuario.Listar();
        //ArrayList<TipoUsuario> tipoUsuarios = (ArrayList<TipoUsuario>) dataTipoUsuario.Listar();
        model.addAttribute("usuarios", usuarios);
        //model.addAttribute("tipoUsuarios", tipoUsuarios);
        return "listadoUsuario";
    }

    @GetMapping("/nuevoUsuario")
    public String nuevoUsuario(Model model) {
        model.addAttribute("opcionEditar", 0);
        return "usuarioFormular";
    }

    @RequestMapping(value = "/registrarUsuario", method = RequestMethod.POST)
    public String registroUsuario(@RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono,
            @RequestParam("nombreUsuario") String nombreUsuario,
            @RequestParam("clave") String clave,
            //@RequestParam("tipoUsuario") TipoUsuario tipoUsuario,
            Model model) {

        // Crear Persona
        Persona p = new Persona();
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setEmail(email);
        p.setTelefono(telefono);
        // Registrar Persona y recuperarlo
        Persona persona = dataPersona.Registrar(p);

        // Crear Usuario
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setClave(clave);
        usuario.setIdPersona(persona);
        usuario.setEstado(1);
        //usuario.setIdTipoUsuario(tipoUsuario);

        // Registrar Usuario
        dataUsuario.Guardar(usuario);

        // Listar Usuarios        
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) dataUsuario.Listar();
        //ArrayList<TipoUsuario> tipoUsuarios = (ArrayList<TipoUsuario>) dataTipoUsuario.Listar();
        model.addAttribute("usuarios", usuarios);
        //model.addAttribute("tipoUsuarios", tipoUsuarios);
        return "listadoUsuario";
    }

    @GetMapping("/eliminarUsuario")
    public String eliminarUsuario(int idUsuario, Model model) {
        // Eliminar
        dataUsuario.Eliminar(idUsuario);

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) dataUsuario.Listar();
        //ArrayList<TipoUsuario> tipoUsuarios = (ArrayList<TipoUsuario>) dataTipoUsuario.Listar();

        model.addAttribute("usuarios", usuarios);
        //model.addAttribute("tipoUsuarios", tipoUsuarios);
        return "listadoUsuario";
    }

    // Buscar Usuario - Editar
    @GetMapping("/buscarUsuario")
    public String buscarUsuario(@RequestParam(value = "idUsuario") int idUsuario, Model model) {
        Optional<Usuario> usuario = dataUsuario.ListarId(idUsuario);
        //ArrayList<TipoUsuario> tipoUsuarios = (ArrayList<TipoUsuario>) dataTipoUsuario.Listar();

        //model.addAttribute("tipoUsuarios", tipoUsuarios);
        model.addAttribute("usuario", usuario.get());
        model.addAttribute("opcionEditar", 1);
        model.addAttribute("opcion", "actualizarUsuario");
        return "usuarioFormular";
    }

    // Actualizar Usuario
    @RequestMapping(value = "/actualizarUsuario", method = RequestMethod.POST)
    public String actualizarUsuario(@RequestParam("idUsuario") String idUsuario,
            @RequestParam("idPersona") String idPersona,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono,
            @RequestParam("estado") int estado,
            @RequestParam("nombreUsuario") String nombreUsuario,
            @RequestParam("clave") String clave,
            //@RequestParam("tipoUsuario") TipoUsuario tipoUsuario,
            Model model) {

        int idU = Integer.valueOf(idUsuario);
        int idP = Integer.valueOf(idPersona);

        // Crear Persona
        Persona p = new Persona();
        p.setIdPersona(idP);
        p.setApellido(apellido);
        p.setEmail(email);
        p.setNombre(nombre);
        p.setTelefono(telefono);
        dataPersona.Guardar(p);

        // Crear Usuario
        Usuario u = new Usuario();
        u.setIdUsuario(idU);
        u.setEstado(estado);
        u.setClave(clave);
        u.setNombreUsuario(nombreUsuario);
        u.setIdPersona(dataPersona.ListarId(idP).get());
        //u.setIdTipoUsuario(tipoUsuario);
        dataUsuario.Guardar(u);

        // Listar
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) dataUsuario.Listar();
        //ArrayList<TipoUsuario> tipoUsuarios = (ArrayList<TipoUsuario>) dataTipoUsuario.Listar();

        model.addAttribute("usuarios", usuarios);
        //model.addAttribute("tipoUsuarios", tipoUsuarios);
        return "listadoUsuario";
    }

    /**
     * *****Carrito de compras*****
     */
    @GetMapping("/carritoCompras")
    public String carroCompas(Model model) {
        return "CarritoV";
    }

    ///MODULO DE PRODUCTOS    
    @GetMapping("/gestionProductos")
    public String listarProductos(Model model) {
        ArrayList<Producto> productos = (ArrayList<Producto>) dataProducto.Listar();

        //ArrayList<TipoUsuario> tipoUsuarios = (ArrayList<TipoUsuario>) dataTipoUsuario.Listar();
        model.addAttribute("productos", productos);
        return "gestionProducto";
    }

    @GetMapping("/nuevoProducto")
    public String nuevoProducto(Model model) {
        model.addAttribute("opcionEditar", 0);
        return "gestionProductoFormulario";
    }

    @RequestMapping(value = "/registrarProducto", method = RequestMethod.POST)
    public String registroProducto(@RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            //@RequestParam("imagen") MultipartFile imagen,
            Model model) {

        // Crear Producto
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setEstado(1);
        p.setPrecio(precio);

        /*if (!imagen.isEmpty()) {
            Path directorioImagen = Paths.get("src//main//resources//static/images");
            String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();
            //String rutaAbsoluta = "C://Producto//recursos";

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                //Setear imagen
                p.setImagen(imagen.getOriginalFilename());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }*/
        // Registrar Producto
        dataProducto.Guardar(p);
        // Recuperar listado
        ArrayList<Producto> productos = (ArrayList<Producto>) dataProducto.Listar();
        model.addAttribute("productos", productos);

        return "gestionProducto";
    }

    // Buscar Producto - Editar
    @GetMapping("/buscarProducto")
    public String buscarProducto(@RequestParam(value = "idProducto") int idProducto, Model model) {
        Optional<Producto> producto = dataProducto.ListarId(idProducto);

        //model.addAttribute("tipoUsuarios", tipoUsuarios);
        model.addAttribute("producto", producto.get());
        model.addAttribute("opcionEditar", 1);
        model.addAttribute("opcion", "actualizarProducto");
        //model.addAttribute("opcion", "actualizarUsuario");
        return "gestionProductoFormulario";
    }

    // Actualizar Producto
    @RequestMapping(value = "/actualizarProducto", method = RequestMethod.POST)
    public String actualizarProducto(@RequestParam("idProducto") int idProducto,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            @RequestParam("estado") int estado,
            //@RequestParam("imagen") MultipartFile imagen,
            Model model) {

        // Crear Producto
        Producto p = new Producto();
        p.setIdProducto(idProducto);
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setEstado(estado);
        //p.setImagen(imagen);
        dataProducto.Guardar(p);

        // Listar
        ArrayList<Producto> productos = (ArrayList<Producto>) dataProducto.Listar();

        model.addAttribute("productos", productos);
        return "gestionProducto";
    }    
}
