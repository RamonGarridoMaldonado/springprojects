package com.rgarmal.springproject.tienda.controllers;
import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import java.io.IOException;
import java.util.*;   
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import com.rgarmal.springproject.tienda.model.Cliente;
import com.rgarmal.springproject.tienda.model.Producto;
import com.rgarmal.springproject.tienda.services.ClientesServices;

@Controller
@RequestMapping("clientes")
public class ClienteController {
    
    @Autowired
    ClientesServices clientesServices;
    
    @Value("${pagination.size}")
    int sizePage;

    @GetMapping(value = "/list")
    public ModelAndView list(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:list/1/codigo/asc");
        return modelAndView;
    }

    @GetMapping(value = "/list/{numPage}/{fieldSort}/{directionSort}")
    public ModelAndView listPage(Model model,
            @PathVariable("numPage") Integer numPage,
            @PathVariable("fieldSort") String fieldSort,
            @PathVariable("directionSort") String directionSort) {


        Pageable pageable = PageRequest.of(numPage - 1, sizePage,
            directionSort.equals("asc") ? Sort.by(fieldSort).ascending() : Sort.by(fieldSort).descending());

        Page<Cliente> page = clientesServices.findAll(pageable);

        List<Cliente> clientes = page.getContent();

        ModelAndView modelAndView = new ModelAndView("clientes/listaClientes");
        modelAndView.addObject("clientes", clientes);

        modelAndView.addObject("numPage", numPage);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());

        modelAndView.addObject("fieldSort", fieldSort);
        modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

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
        //List<Cliente> clientes = clientesServices.findAll();
        //modelAndView.addObject("clientes", clientes);
        modelAndView.setViewName("redirect:modificarCliente?codigo="+cliente.getCodigo());
        return modelAndView;
    }

    @RequestMapping(value = {"/nuevoCliente"} )
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clientes/nuevoCliente");
        return modelAndView;
    }

    @PostMapping(value = "/newCliente")
    public ModelAndView saveCliente(Cliente cliente) throws IOException {
        clientesServices.insert(cliente);
        ModelAndView modelAndView = new ModelAndView();
        //List<Producto> productos = ProductosService.findAll();
        //modelAndView.addObject("productos", productos);
        modelAndView.setViewName("redirect:modificarCliente?codigo="+cliente.getCodigo());
        return modelAndView;
    }


    @RequestMapping(value = "/borrarCliente")
    public ModelAndView delete(Cliente cliente) {
        clientesServices.delete(cliente);
        //List<Cliente> clientes = clientesServices.findAll();
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("clientes",clientes);
        modelAndView.setViewName("redirect:list/1/codigo/asc");
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
