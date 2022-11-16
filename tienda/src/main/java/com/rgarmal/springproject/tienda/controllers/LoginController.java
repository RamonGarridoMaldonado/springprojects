package com.rgarmal.springproject.tienda.controllers;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import com.rgarmal.springproject.tienda.model.LoginUsuario;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private MessageSource messageSource;


    @GetMapping(value = "/signin")
    public String inicio(Model model,HttpSession session) {
        session.getAttribute("usuario");
        return "signin";
    }

    @GetMapping(value = {"/wellcome"}) 
        public String welcome() {
            return "login/wellcome";
        }

    @PostMapping(value = {"/wellcome"} )
    public ModelAndView guardarUsuario(Model model,LoginUsuario usuario, HttpSession session) {

        session.setAttribute("usuario", usuario);
        ModelAndView modelAndView = new ModelAndView();
        String mensaje = messageSource.getMessage("saludar.usuario", new String[]{usuario.getUsuario()}, LocaleContextHolder.getLocale());
        modelAndView.addObject("User", mensaje);
        model.addAttribute("usuario", usuario.getUsuario());
        modelAndView.setViewName("login/wellcome");
        return modelAndView;
    }

    @GetMapping(value = {"/logout"}) 
    public String logout(HttpSession session) {
        session.getAttribute("usuario");
        session.invalidate();
        return "login/signin";
    }
}
