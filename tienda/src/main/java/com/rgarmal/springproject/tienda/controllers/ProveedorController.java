package com.rgarmal.springproject.tienda.controllers;
import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;   

import com.rgarmal.springproject.tienda.model.Proveedor;

@Controller
@RequestMapping("proveedores")
public class ProveedorController {
    
    @RequestMapping(value = "/listaProveedores")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedores", getProveedores());
        modelAndView.setViewName("proveedores/listaProveedores");
        return modelAndView;
    }

    @RequestMapping(value = {"/modificarProveedor"} )
    public ModelAndView edit(
        @RequestParam(name="codigo",required = true) int codigo
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedores", getProveedor(codigo));
        modelAndView.setViewName("proveedores/modificarProveedor");
        return modelAndView;
    }

    @PostMapping(value = "/editProveedor")
    public ModelAndView editProveedor(Proveedor proveedor) {
        ModelAndView modelAndView = new ModelAndView();
        List proveedores = getProveedores();
        int indexOf = proveedores.indexOf(proveedor);
        proveedores.set(indexOf,proveedor);
        modelAndView.addObject("proveedores", proveedores);
        modelAndView.setViewName("proveedores/nuevoProveedor");
        return modelAndView;
    }

    @RequestMapping(value = {"/nuevoProveedor"} )
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("proveedores/nuevoProveedor");
        return modelAndView;
    }

    @RequestMapping(value = "/newProveedor")
    public ModelAndView saveProveedor(Proveedor proveedor) {
        ModelAndView modelAndView = new ModelAndView();
        List proveedores = getProveedores();
        proveedores.add(proveedor);
        modelAndView.addObject("proveedores", proveedores);
        modelAndView.setViewName("proveedores/listaProveedores");
        return modelAndView;
    }


    @RequestMapping(value = "/borrarProveedor")
    public ModelAndView delete(
        @RequestParam(name="codigo",required = true) int codigo
    ) {
        List<Proveedor> proveedores = getProveedores();
        proveedores.remove(new Proveedor(codigo));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedores",proveedores);
        modelAndView.setViewName("proveedores/listaProveedores");
        return modelAndView;
    }

    private Proveedor getProveedor(int codigo) {
        List<Proveedor> proveedores = getProveedores();
        int indexOf = proveedores.indexOf(new Proveedor(codigo));
        
        return proveedores.get(indexOf);
    }

    private List<Proveedor> getProveedores() {
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        proveedores.add(new Proveedor(1,"ramon","garrido"));
        proveedores.add(new Proveedor(2,"pepe","villarejo"));
        proveedores.add(new Proveedor(3,"antonio","perez"));
        proveedores.add(new Proveedor(4,"jose","baeza"));
        return proveedores;
    }


}
