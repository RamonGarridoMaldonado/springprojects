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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;   

import com.rgarmal.springproject.tienda.model.Cliente;
import com.rgarmal.springproject.tienda.services.ClientesServices;

@Controller
@RequestMapping("clientes")
public class ClienteController {
    
    @Autowired
    ClientesServices clientesServices;

    @RequestMapping(value = "/listaClientes")
    public ModelAndView list() {
        List<Cliente> clientes = clientesServices.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientes", clientes);
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

    @PostMapping(value = "/editCliente")
    public ModelAndView editCliente(Cliente cliente) {
        ModelAndView modelAndView = new ModelAndView();
        clientesServices.update(cliente);
        List<Cliente> clientes = clientesServices.findAll();
        modelAndView.addObject("clientes", clientes);
        modelAndView.setViewName("clientes/listaClientes");
        return modelAndView;
    }

    @RequestMapping(value = {"/nuevoCliente"} )
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clientes/nuevoCliente");
        return modelAndView;
    }

    @PostMapping(value = "/newCliente")
    public ModelAndView saveCliente(Cliente cliente) {
        ModelAndView modelAndView = new ModelAndView();
        clientesServices.insert(cliente);
        List<Cliente> clientes = clientesServices.findAll();
        modelAndView.addObject("clientes", clientes);
        modelAndView.setViewName("clientes/listaClientes");
        return modelAndView;
    }


    @RequestMapping(value = "/borrarCliente")
    public ModelAndView delete(Cliente cliente) {
        clientesServices.delete(cliente);
        List<Cliente> clientes = clientesServices.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientes",clientes);
        modelAndView.setViewName("clientes/listaClientes");
        return modelAndView;
    }

    private Cliente getCliente(int codigo) {
        Cliente clientes = clientesServices.findById(codigo);
        //int indexOf = clientes.indexOf(new Cliente(codigo));
        
        //return clientes.get(indexOf);
        return clientes;
    }

    /*private List<Cliente> getClientes() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(1,"ramon","garrido","ramon@gmail.com","2323423Q","1233123","inventada",true));
        clientes.add(new Cliente(2,"pepe","villarejo","pepe@gmail.com","2367534P","65464564","inventada2",true));
        clientes.add(new Cliente(3,"antonio","perez","antonio@gmail.com","232751K","45345","inventada3",false));
        clientes.add(new Cliente(4,"jose","baeza","jose@gmail.com","23237673O","123756","inventada4",true));
        return clientes;
    }*/


}
