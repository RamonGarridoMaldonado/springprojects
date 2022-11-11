package com.rgarmal.springproject.tienda.controllers;
import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;   

import com.rgarmal.springproject.tienda.model.Producto;

@Controller
@RequestMapping("productos")
public class ProductoController {
    
    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productos", getProductos());
        modelAndView.setViewName("productos/list");
        return modelAndView;
    }

    @GetMapping(value = "/inicio")
    public String inicio(Model model) {
        model.addAttribute("productos", getProductos());
        return "list";
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(
        @RequestParam(name="codigo",required = true) int codigo
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("producto", getProducto(codigo));
        modelAndView.setViewName("productos/edit");
        return modelAndView;
    }

    private Producto getProducto(int codigo) {
        List<Producto> productos = getProductos();
        int indexOf = productos.indexOf(new Producto(codigo));
        
        return productos.get(indexOf);
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
        productos.add(new Producto(0,"Hamburguesa","La hamburguesa esta muy rica","/img/hamburguesa.jpg"));
        productos.add(new Producto(1,"pizza","La pizza esta muy rica","/img/pizza.jpg"));
        productos.add(new Producto(2,"perrito caliente","los hot-dog estan muy ricos","/img/perrito-caliente.jpg"));
        productos.add(new Producto(3,"coca cola","La coca-cola esta muy rica","/img/cocacola.jpg"));
        return productos;
    }
}
