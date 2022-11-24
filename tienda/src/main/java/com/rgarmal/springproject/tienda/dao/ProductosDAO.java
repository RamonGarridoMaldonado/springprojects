package com.rgarmal.springproject.tienda.dao;
import java.util.List;

import com.rgarmal.springproject.tienda.model.Producto;

public interface ProductosDAO {
    public List<Producto> findAll();

    public Producto findById(int codigo);

    public void insert (Producto producto);

    public void update (Producto producto);

    public void delete (Producto producto);
}
