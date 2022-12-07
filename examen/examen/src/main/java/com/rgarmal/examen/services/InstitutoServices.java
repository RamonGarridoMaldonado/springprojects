package com.rgarmal.examen.services;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rgarmal.examen.model.Alumno;

public interface InstitutoServices {
    public Page<Alumno> findAll(Pageable page);

    public Alumno findById(int codigo);

    public void insert(Alumno alumno);

    public void update(Alumno alumno);

    public void updateImagen(Alumno alumno);

    public void delete(Alumno alumno);
}
