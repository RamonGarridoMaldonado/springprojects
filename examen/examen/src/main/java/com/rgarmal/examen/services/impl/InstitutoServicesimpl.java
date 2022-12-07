package com.rgarmal.examen.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rgarmal.examen.dao.InstitutoDAO;
import com.rgarmal.examen.model.Alumno;
import com.rgarmal.examen.services.InstitutoServices;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Service
public class InstitutoServicesimpl implements InstitutoServices {

    @Autowired
    InstitutoDAO institutoDAO;

    @Override
    public Page<Alumno> findAll(Pageable page) {
        return institutoDAO.findAll(page);

    }

    @Override
    public Alumno findById(int codigo) {
        return institutoDAO.findById(codigo);

    }

    @Override
    public void insert(Alumno alumno) {
        institutoDAO.insert(alumno);

    }

    @Override
    public void update(Alumno alumno) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateImagen(Alumno alumno) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Alumno alumno) {
        institutoDAO.delete(alumno);
    }
    
}
