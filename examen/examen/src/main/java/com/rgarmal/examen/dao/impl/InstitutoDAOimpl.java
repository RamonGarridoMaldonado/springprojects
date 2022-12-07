package com.rgarmal.examen.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import javax.sql.DataSource;

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

import com.rgarmal.examen.dao.InstitutoDAO;
import com.rgarmal.examen.model.Alumno;
import javax.annotation.PostConstruct;

@Repository
public class InstitutoDAOimpl extends JdbcDaoSupport implements InstitutoDAO {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }
    

    @Override
    public Page<Alumno> findAll(Pageable page) {
        String queryCount = "select count(1) from alumno";
        Integer total = getJdbcTemplate().queryForObject(queryCount,Integer.class);


        Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Order.by("codigo");

        String query = "SELECT * FROM alumno ORDER BY " + order.getProperty() + " "
        + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset();

        final List<Alumno> productos = getJdbcTemplate().query(query, new RowMapper<Alumno>() {

            @Override
            @Nullable
            public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
                Alumno alumno = new Alumno();
                alumno.setCodigo(rs.getInt("codigo"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setDni(rs.getString("dni"));
                alumno.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                alumno.setEmail(rs.getString("email"));
                alumno.setNuevo(rs.getBoolean("nuevo"));

                return alumno;
            }
            
        });

        

        return new PageImpl<Alumno>(productos, page, total);
    }

    @Override
    public Alumno findById(int codigo) {
        String query = "select * from alumno where codigo = ?";
        Object params [] = {codigo};
        int types [] = {Types.INTEGER};

        Alumno productos = (Alumno) getJdbcTemplate().queryForObject(query,params,types,new BeanPropertyRowMapper(Alumno.class));
        return productos;
    }

    @Override
    public void insert(Alumno alumno) {
        String query = "insert into alumno (codigo," + 
        " nombre," + 
        " apellidos," + 
        " dni, " +
        "fecha_nacimiento,"+
        "email,"+
        "nuevo)"+ 
        " values (?, ?, ?, ?)";

KeyHolder keyHolder = new GeneratedKeyHolder();

getJdbcTemplate().update(new PreparedStatementCreator() {

@Override
public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,alumno.getCodigo());
        ps.setString(2, alumno.getNombre());
        ps.setString(3, alumno.getApellidos());
        ps.setString(4, alumno.getDni());
        ps.setDate(5, (Date) alumno.getFechaNacimiento());
        ps.setString(6, alumno.getEmail());
        ps.setBoolean(7, alumno.isNuevo());
        return ps;
}
}, keyHolder);

alumno.setCodigo(keyHolder.getKey().intValue());

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
        String query = " delete from alumno where codigo = ?";
        Object[] params = {
            alumno.getCodigo(),
        };

        final int[] types = {
            Types.INTEGER,
        };
        int update = getJdbcTemplate().update(query,params,types);        
    }
    
}
