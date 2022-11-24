package com.rgarmal.springproject.tienda.controllers;
import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;   

import com.rgarmal.springproject.tienda.model.Producto;
import com.rgarmal.springproject.tienda.services.ClientesServices;
import com.rgarmal.springproject.tienda.services.ProductosService;

@Controller
@RequestMapping("productos")
public class ProductoController {
    
    @Autowired
    ProductosService ProductosService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        List<Producto> productos = ProductosService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productos", productos);
        modelAndView.setViewName("productos/list");
        return modelAndView;
    }

    @GetMapping(value = "/inicio")
    public String inicio(Model model) {
        model.addAttribute("productos", getProductos());
        return "list";
    }


    private Producto getProducto(int codigo) {
        Producto productos = ProductosService.findById(codigo);
        return productos;
    }

    @GetMapping(path = {"/producto"})
    public ModelAndView producto(@RequestParam(name = "codigo",required = true) int codigo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("producto", getProducto(codigo));
        modelAndView.setViewName("producto");
        return modelAndView;
    }


    @GetMapping(path = {"/producto/{codigo}"})
    public ModelAndView product (@PathVariable(name = "codigo",required = true) int codigo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("producto", getProducto(codigo));
        modelAndView.setViewName("producto");
        return modelAndView;
    }

    private List<Producto> getProductos() {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        return productos;
    }

    @RequestMapping(value = {"/nuevoProducto"} )
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productos/nuevoProducto");
        return modelAndView;
    }

    @PostMapping(value = "/newProducto")
    public ModelAndView saveCliente(Producto producto) {
        ModelAndView modelAndView = new ModelAndView();
        ProductosService.insert(producto);
        List<Producto> productos = ProductosService.findAll();
        modelAndView.addObject("productos", productos);
        modelAndView.setViewName("productos/list");
        return modelAndView;
    }

    @RequestMapping(value = "/borrarProducto")
    public ModelAndView delete(Producto producto) {
        ProductosService.delete(producto);
        List<Producto> productos = ProductosService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productos",productos);
        modelAndView.setViewName("productos/list");
        return modelAndView;
    }

    @RequestMapping(value = {"/modificarProducto"} )
    public ModelAndView edit(
        @RequestParam(name="codigo",required = true) int codigo
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("producto", getProducto(codigo));
        modelAndView.setViewName("productos/modificarProducto");
        return modelAndView;
    }

    @PostMapping(value = "/editProducto")
    public ModelAndView editCliente(Producto producto) {
        ModelAndView modelAndView = new ModelAndView();
        ProductosService.update(producto);
        List<Producto> clientes = ProductosService.findAll();
        modelAndView.addObject("productos", clientes);
        modelAndView.setViewName("productos/list");
        return modelAndView;
    }
}
