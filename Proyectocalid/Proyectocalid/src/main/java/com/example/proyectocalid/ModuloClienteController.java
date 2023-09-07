package com.example.proyectocalid;

import com.example.proyectocalid.modelo.Carrito;
import com.example.proyectocalid.modelo.Cliente;
import com.example.proyectocalid.modelo.Delivery;
import com.example.proyectocalid.modelo.Pago;
import com.example.proyectocalid.modelo.Producto;
import com.example.proyectocalid.modelo.Tarjeta;
import com.example.proyectocalid.modelo.Venta;
import com.example.proyectocalid.modelo.VentaDetalle;
import com.example.proyectocalid.service.ICarritoService;
import com.example.proyectocalid.service.IClienteService;
import com.example.proyectocalid.service.IDeliveryService;
import com.example.proyectocalid.service.IPagoService;
import com.example.proyectocalid.service.IProductoService;
import com.example.proyectocalid.service.ITarjetaService;
import com.example.proyectocalid.service.IVentaDetalleService;
import com.example.proyectocalid.service.IVentaService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModuloClienteController {

    
    @Autowired
    private IClienteService dataCliente;
    @Autowired
    private IVentaService dataVenta;
    @Autowired
    private IPagoService dataPago;
    @Autowired
    private ITarjetaService dataTarjeta;
    @Autowired
    private IVentaDetalleService dataVentaDetalle;
    @Autowired
    private IProductoService dataProducto;
    @Autowired
    private ICarritoService dataCarrito;
    @Autowired
    private IDeliveryService dataDelivery;

    //CLIENTE LOGUEADO
    private Cliente log;

    //MODULO CLIENTE CLIENTE
    @GetMapping("/elDorado")
    public String inicio(Model model) {
        model.addAttribute("aux", 0);
        return "ModuloCliente/loginCliente";
    }

    //LOGIN CLIENTE
    @RequestMapping(value = "/IngresarCliente", method = RequestMethod.POST)
    public String loguearse(@RequestParam("user") String usr, @RequestParam("pass") String pss, Model model) {
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) dataCliente.clientesActivos();

        for (Cliente user : clientes) {
            if (user.getNombreUsuario().equals(usr) && user.getClave().equals(pss)) {
                this.log = user;
                return mostrarMenuCliente(model);
            }
        }
        return inicio(model);
    }

    //MENU CLIENTE
    @GetMapping("/MenuCliente")
    public String mostrarMenuCliente(Model model) {
        /*if (log != null) {
            model.addAttribute("usuario", log);
            return "index";
        } else {
            return "login";
        }*/
        return "ModuloCliente/indexCliente";
    }

    //LISTAR PRODUCTOS
    @GetMapping("/listadoProductos")
    public String listarProductos(Model model) {
        ArrayList<Producto> productos = (ArrayList<Producto>) dataProducto.productosActivos();
        model.addAttribute("productos", productos);
        return "ModuloCliente/listadoProductos";
    }

    // AGREGAR A CARRITO
    @GetMapping("/agregarCarrito")
    public String agregarCarrito(@RequestParam(value = "idProducto") int idProducto, Model model) {
        String fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        //Recuperar datos
        Optional<Producto> producto = dataProducto.ListarId(idProducto);
        Carrito carrito = dataCarrito.buscarCarritoDeCliente(this.log.getIdCliente());
        System.out.println("Prueba de cliente" + log.getIdCliente());
        System.out.println("Prueba de carrito" + carrito);
        if (carrito == null) { //CUANDO NO TIENE DATA EN CARRITO
            //Crear Venta
            Venta venta = new Venta();
            venta.setCancelado(0);
            venta.setFecha(fecha);
            venta.setIdCliente(this.log);
            //Registrar Venta y recuperar
            Venta v = dataVenta.Registrar(venta);

            //Crear Detalle de Venta
            VentaDetalle ventaDetalle = new VentaDetalle();
            ventaDetalle.setIdVenta(v);
            ventaDetalle.setIdProducto(producto.get());
            ventaDetalle.setCantidad(1);
            ventaDetalle.setPrecio(producto.get().getPrecio());
            ventaDetalle.setTotal(producto.get().getPrecio());
            //Registrar
            dataVentaDetalle.Guardar(ventaDetalle);

            //Crear Carrito
            Carrito car = new Carrito();
            car.setCancelado(0);
            car.setIdCliente(this.log);
            car.setIdVenta(v);
            //Registrar
            dataCarrito.Guardar(car);
        } else { //CUANDO TIENE DATA EN CARRITO
            //Recuperar Venta
            Optional<Venta> venta = dataVenta.ListarId(carrito.getIdVenta().getIdVenta());

            //Agregar Detalle de Venta
            VentaDetalle ventaDetalle = new VentaDetalle();
            ventaDetalle.setIdVenta(venta.get());
            ventaDetalle.setIdProducto(producto.get());
            ventaDetalle.setCantidad(1);
            ventaDetalle.setPrecio(producto.get().getPrecio());
            ventaDetalle.setTotal(producto.get().getPrecio());
            //Registrar
            dataVentaDetalle.Guardar(ventaDetalle);
        }
        return listarProductos(model);
    }

    // DETALLE DE CARRITO
    @GetMapping("/detalleCarrito")
    public String detalleCarrito(Model model) {
        Carrito carrito = dataCarrito.buscarCarritoDeCliente(this.log.getIdCliente());
        if (carrito == null) {
            model.addAttribute("filtroVentas", null);
            model.addAttribute("montoTotal", 0.0);
        } else {
            // Recuperar 
            ArrayList<VentaDetalle> filtroVentas = dataVentaDetalle.buscarPoridVenta(carrito.getIdVenta().getIdVenta());
            // Datos a compartir a Carrito
            model.addAttribute("filtroVentas", filtroVentas);
            model.addAttribute("montoTotal", dataVentaDetalle.obtenerMontoTotalVenta(carrito.getIdVenta().getIdVenta()));
        }
        return "ModuloCliente/carritoCompras";
    }

    // BUSCAR DETALLE DE UN PRODUCTO
    @GetMapping("/buscarVentaDetalle")
    public String irEditarVentaDetalle(@RequestParam(value = "idVentaDetalle") int idVentaDetalle,
            Model model) {
        Optional<VentaDetalle> ventaDetalle = dataVentaDetalle.ListarId(idVentaDetalle);
        model.addAttribute("ventaDetalle", ventaDetalle.get());
        return "ModuloCliente/ventaDetalleEditar";
    }

    // ACTUALIZAR DETALLE DE PRODUCTO
    @RequestMapping(value = "/actualizarVentaDetalle", method = RequestMethod.POST)
    public String actualizarVentaDetalle(@RequestParam(value = "idVentaDetalle") int idVentaDetalle,
            @RequestParam("cantidad") int cantidad,
            Model model) {
        Optional<VentaDetalle> ventaDetalle = dataVentaDetalle.ListarId(idVentaDetalle);
        ventaDetalle.get().setCantidad(cantidad);
        //Nuevo precio
        Double total = ventaDetalle.get().getPrecio() * cantidad;
        ventaDetalle.get().setTotal(total);
        dataVentaDetalle.Guardar(ventaDetalle.get());
        return detalleCarrito(model);
    }

    // ELIMINAR PRODUCTO DE VENTA DETALLE
    @GetMapping("/eliminarVentaDetalle")
    public String eliminarVentaDetalle(int idVentaDetalle, Model model) {
        //Eliminar
        dataVentaDetalle.Eliminar(idVentaDetalle);
        return detalleCarrito(model);
    }

    //LISTAR PRODUCTOS
    @GetMapping("/resumenPago")
    public String resumenPago(Model model) {
        return "ModuloCliente/realizarPago";
    }

    //REGISTRO DE COMPRA
    @RequestMapping(value = "/confirmarPago", method = RequestMethod.POST)
    public String confirmarPago(@RequestParam(value = "direccion") String direccion,
            @RequestParam(value = "numeroCelular") String numeroCelular,
            @RequestParam(value = "nombre") String nombre,
            @RequestParam(value = "cvv") String cvv,
            @RequestParam(value = "numero") String numero,
            @RequestParam(value = "fecha") String fecha,
            Model model) {
        String fechaActual = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        
        Carrito carrito = dataCarrito.buscarCarritoDeCliente(this.log.getIdCliente());
        // Recuperar 
        ArrayList<VentaDetalle> filtroVentas = dataVentaDetalle.buscarPoridVenta(carrito.getIdVenta().getIdVenta());

        //Actualizar Venta
        Optional<Venta> venta = dataVenta.ListarId(carrito.getIdVenta().getIdVenta());
        venta.get().setCancelado(1);
        venta.get().setPagoTotal(dataVentaDetalle.obtenerMontoTotalVenta(carrito.getIdVenta().getIdVenta()));
        //Registrar
        dataVenta.Guardar(venta.get());

        //Actualizar Carrito
        carrito.setCancelado(1);
        //Registrar
        dataCarrito.Guardar(carrito);

        // Registrar tarjeta
        Tarjeta t = new Tarjeta();
        t.setNombre(nombre);
        t.setCvv(cvv);
        t.setNumeroTarjeta(numero);
        t.setFechaVencimiento(fecha);
        // Guardar y recuperar
        Tarjeta tarjeta = dataTarjeta.Registrar(t);

        // Registrar Pago
        Pago pago = new Pago();
        pago.setFecha(fechaActual);
        pago.setIdVenta(venta.get());
        pago.setIdTarjeta(tarjeta);
        dataPago.Guardar(pago);

        //Registrar Delivery
        Delivery delivery = new Delivery();
        delivery.setDireccion(direccion);
        delivery.setNumeroCelular(numeroCelular);
        delivery.setIdVenta(venta.get());
        delivery.setEstado("En proceso");
        //Guardar
        dataDelivery.Guardar(delivery);
        return listadoPedidos(model);
    }

    // LISTADO DE PEDIDOS
    @GetMapping("/listadoPedidos")
    public String listadoPedidos(Model model) {
        ArrayList<Delivery> deliverys = dataDelivery.buscarDeliveriesDeCliente(this.log.getIdCliente());
        model.addAttribute("deliverys", deliverys);
        return "ModuloCliente/listadoPedidos";
    }
}
