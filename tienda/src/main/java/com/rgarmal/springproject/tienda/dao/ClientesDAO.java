package com.rgarmal.springproject.tienda.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rgarmal.springproject.tienda.model.Cliente;

public interface ClientesDAO {
    public Page<Cliente> findAll(Pageable page);

    public Cliente findById(int codigo);

    public void insert (Cliente cliente);

    public void update (Cliente cliente);

    public void delete (Cliente cliente);


}
