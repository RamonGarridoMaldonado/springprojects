package com.rgarmal.springproject.tienda.services.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgarmal.springproject.tienda.dao.ClientesDAO;
import com.rgarmal.springproject.tienda.model.Cliente;
import com.rgarmal.springproject.tienda.model.Producto;
import com.rgarmal.springproject.tienda.services.ClientesServices;

@Service
public class ClientesServiceImpl implements ClientesServices{

    @Autowired
    ClientesDAO clientesDAO;

    @Override
    public List<Cliente> findAll() {
        return clientesDAO.findAll();
    }


    @Override
    public Cliente findById(int codigo) {
        return clientesDAO.findById(codigo);
    }

    @Override 
    public void insert(Cliente cliente) {
        clientesDAO.insert(cliente);
    } 

    @Override 
    public void update(Cliente cliente) {
        clientesDAO.update(cliente);
    } 
    
    @Override 
    public void delete(Cliente cliente) {
        clientesDAO.delete(cliente);
    } 
}