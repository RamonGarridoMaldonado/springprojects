package com.rgarmal.springproject.tienda.dao.impl;

import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.rgarmal.springproject.tienda.dao.ProductosDAO;
import com.rgarmal.springproject.tienda.model.Producto;

@Repository
public class ProductosDAOimpl extends JdbcDaoSupport implements ProductosDAO {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }
    
    @Override
    public List<Producto> findAll() {
        String query = "select * from Producto";
        List<Producto> productos = getJdbcTemplate().query(query,new BeanPropertyRowMapper(Producto.class));
        return productos;
    }

    @Override
    public Producto findById(int codigo) {
        String query = "select * from Producto where codigo = ?";
        Object params [] = {codigo};
        int types [] = {Types.INTEGER};

        Producto productos = (Producto) getJdbcTemplate().queryForObject(query,params,types,new BeanPropertyRowMapper(Producto.class));
        return productos;
    }

    @Override
    public void insert(Producto producto) {
        String query = "insert into Producto (nombre,"+
                                            "descripcion,"+
                                            "precio,"+
                                            "fecha)"+
                                            "values (?, ?, ?, ?)";
        Object[] params = {
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getFecha(),
        };

        final int[] types = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.FLOAT,
            Types.DATE,
        };

        int update = getJdbcTemplate().update(query,params,types);        
    }

    @Override
    public void update(Producto producto) {
        String query = " update Producto set nombre=?,"+
                                            "descripcion=?,"+
                                            "precio=?,"+
                                            "fecha=? "+
                                            "where codigo=?";
        Object[] params = {
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getFecha(),
            producto.getCodigo(),
        };

        final int[] types = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.FLOAT,
            Types.DATE,
            Types.INTEGER,
        };

        int update = getJdbcTemplate().update(query,params,types);        
    }

    @Override
    public void delete(Producto producto) {
        String query = " delete from Producto where codigo = ?";
        Object[] params = {
            producto.getCodigo(),
        };

        final int[] types = {
            Types.INTEGER,
        };
        int update = getJdbcTemplate().update(query,params,types);
    }
    
}
