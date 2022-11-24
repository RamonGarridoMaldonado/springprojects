package com.rgarmal.springproject.tienda.services.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgarmal.springproject.tienda.dao.ProductosDAO;
import com.rgarmal.springproject.tienda.model.Producto;
import com.rgarmal.springproject.tienda.services.ProductosService;

@Service
public class ProductosServiceimpl implements ProductosService {

    @Autowired
    ProductosDAO ProductosDAO;

    @Override
    public List<Producto> findAll() {
        return ProductosDAO.findAll();
    }

    @Override
    public Producto findById(int codigo) {
        return ProductosDAO.findById(codigo);

    }

    @Override
    public void insert(Producto producto) {
        ProductosDAO.insert(producto);
        
    }

    @Override
    public void update(Producto producto) {
        ProductosDAO.update(producto);
        
    }

    @Override
    public void delete(Producto producto) {
        ProductosDAO.delete(producto);
        
    }
}
