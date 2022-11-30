package com.rgarmal.springproject.tienda.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rgarmal.springproject.tienda.model.Producto;

public interface ProductosDAO {
    public Page<Producto> findAll(Pageable page);

    public Producto findById(int codigo);

    public void insert (Producto producto);

    public void update (Producto producto);
    
    public void updateImagen (Producto producto);

    public void delete (Producto producto);
}
