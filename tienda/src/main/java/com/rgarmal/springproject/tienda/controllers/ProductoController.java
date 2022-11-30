package com.rgarmal.springproject.tienda.controllers;
import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;   

import com.rgarmal.springproject.tienda.model.Producto;
import com.rgarmal.springproject.tienda.services.ClientesServices;
import com.rgarmal.springproject.tienda.services.ProductosService;

@Controller
@RequestMapping("productos")
public class ProductoController {
    
    @Autowired
    ProductosService ProductosService;

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

        Page<Producto> page = ProductosService.findAll(pageable);

        List<Producto> productos = page.getContent();

        ModelAndView modelAndView = new ModelAndView("productos/list");
        modelAndView.addObject("productos", productos);


        modelAndView.addObject("numPage", numPage);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());

        modelAndView.addObject("fieldSort", fieldSort);
        modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

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
    public ModelAndView saveCliente(Producto producto, @RequestParam("imagenForm") MultipartFile imagen) throws IOException {
        producto.setImagen(imagen.getBytes());
        ProductosService.insert(producto);
        ModelAndView modelAndView = new ModelAndView();
        ProductosService.insert(producto);
        //List<Producto> productos = ProductosService.findAll();
        //modelAndView.addObject("productos", productos);
        modelAndView.setViewName("redirect:modificarProducto?codigo="+producto.getCodigo());
        return modelAndView;
    }

    @RequestMapping(value = "/borrarProducto")
    public ModelAndView delete(Producto producto) {
        ProductosService.delete(producto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:list/1/codigo/asc");
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
    public ModelAndView editCliente(Producto producto, @RequestParam("imagenForm") MultipartFile imagen) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        producto.setImagen(imagen.getBytes());
        ProductosService.update(producto);
        modelAndView.setViewName("redirect:modificarProducto?codigo="+producto.getCodigo());
        return modelAndView;
    }
}
