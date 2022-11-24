package com.rgarmal.springproject.tienda.services;
import java.util.List;

import com.rgarmal.springproject.tienda.model.Cliente;
import com.rgarmal.springproject.tienda.model.Producto;

public interface ClientesServices {

    public List<Cliente> findAll();

    public Cliente findById(int codigo);

    public void insert(Cliente cliente);

    public void update(Cliente cliente);

    public void delete(Cliente cliente);


}