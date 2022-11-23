package com.rgarmal.springproject.tienda.dao;

import java.util.List;

import com.rgarmal.springproject.tienda.model.Cliente;

public interface ClientesDAO {
    public List<Cliente> findAll();

    public Cliente findById(int codigo);

    public void insert (Cliente cliente);

    public void update (Cliente cliente);

    public void delete (Cliente cliente);


}
