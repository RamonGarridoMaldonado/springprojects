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

import com.rgarmal.springproject.tienda.model.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    
    @RequestMapping(value = "/listaClientes")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientes", getClientes());
        modelAndView.setViewName("clientes/listaClientes");
        return modelAndView;
    }

    @RequestMapping(value = {"/modificarCliente"} )
    public ModelAndView edit(
        @RequestParam(name="codigo",required = true) int codigo
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cliente", getCliente(codigo));
        modelAndView.setViewName("clientes/modificarCliente");
        return modelAndView;
    }

    @RequestMapping(value = {"/nuevoCliente"} )
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clientes/nuevoCliente");
        return modelAndView;
    }

    @PostMapping(path = "/newCliente")
    @ResponseBody
    public ModelAndView saveCliente(String codigo, String nombre, String apellidos, String email, String dni, String telefono, String direccion, String vip) {
        ModelAndView modelAndView = new ModelAndView();
        List clientes = getClientes();
        clientes.add(new Cliente(Integer.parseInt(codigo),nombre,apellidos,email,dni,telefono,direccion,Boolean.parseBoolean(vip)));
        modelAndView.addObject("clientes", clientes);
        modelAndView.setViewName("clientes/listaClientes");
        return modelAndView;
    }


    @RequestMapping(value = {"/borrarCliente"})
    public ModelAndView delete(
        @RequestParam(name="codigo",required = true) int codigo
    ) {
        List<Cliente> clientes = getClientes();
        clientes.remove(new Cliente(codigo));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cliente",clientes);
        modelAndView.setViewName("clientes/listaClientes");
        return modelAndView;
    }

    private Cliente getCliente(int codigo) {
        List<Cliente> clientes = getClientes();
        int indexOf = clientes.indexOf(new Cliente(codigo));
        
        return clientes.get(indexOf);
    }


    private List<Cliente> getClientes() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(1,"ramon","garrido","ramon@gmail.com","2323423Q","1233123","inventada",true));
        clientes.add(new Cliente(2,"pepe","villarejo","pepe@gmail.com","2367534P","65464564","inventada2",true));
        clientes.add(new Cliente(3,"antonio","perez","antonio@gmail.com","232751K","45345","inventada3",false));
        clientes.add(new Cliente(4,"jose","baeza","jose@gmail.com","23237673O","123756","inventada4",true));
        return clientes;
    }


}
