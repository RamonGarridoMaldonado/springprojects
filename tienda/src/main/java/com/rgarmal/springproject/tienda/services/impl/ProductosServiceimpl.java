package com.rgarmal.springproject.tienda.services.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rgarmal.springproject.tienda.dao.ProductosDAO;
import com.rgarmal.springproject.tienda.model.Producto;
import com.rgarmal.springproject.tienda.services.ProductosService;

@Service
public class ProductosServiceimpl implements ProductosService {

    @Autowired
    ProductosDAO ProductosDAO;

    @Override
    public Page<Producto> findAll(Pageable page) {
        return ProductosDAO.findAll(page);
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
        if (producto.getImagen() != null && producto.getImagen().length>0) {
            ProductosDAO.updateImagen(producto);
        } 
    }

    @Override
    public void delete(Producto producto) {
        ProductosDAO.delete(producto);

    }

    @Override
    public void updateImagen(Producto producto) {
        ProductosDAO.updateImagen(producto);        
    }
}
