package com.rgarmal.examen.controllers;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rgarmal.examen.model.Alumno;
import com.rgarmal.examen.services.InstitutoServices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;   

@Controller
@RequestMapping("alumnos")
public class InstitutoController {
    
    @Autowired
    InstitutoServices institutoService;

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

        Page<Alumno> page = institutoService.findAll(pageable);

        List<Alumno> productos = page.getContent();

        ModelAndView modelAndView = new ModelAndView("instituto/list");
        modelAndView.addObject("alumnos", productos);

        modelAndView.addObject("numPage", numPage);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());

        modelAndView.addObject("fieldSort", fieldSort);
        modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

        return modelAndView;
    }

    @RequestMapping(value = "/borrarAlumno")
    public ModelAndView delete(Alumno alumno) {
        institutoService.delete(alumno);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:list/1/codigo/asc");
        return modelAndView;
    }

    @RequestMapping(value = {"/nuevoAlumno"} )
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("instituto/nuevoAlumno");
        return modelAndView;
    }

    @PostMapping(value = "/newAlumno")
    public ModelAndView saveCliente(Alumno producto) throws IOException {
        institutoService.insert(producto);
        ModelAndView modelAndView = new ModelAndView();
        //List<Producto> productos = ProductosService.findAll();
        //modelAndView.addObject("productos", productos);
        modelAndView.setViewName("redirect:list/1/codigo/asc");
        return modelAndView;
    }
}
