package com.rgarmal.examen.model;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Alumno {
    private int codigo;
    private String nombre;
    private String apellidos;
    private String dni;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private String email;
    private boolean nuevo;


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
 
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isNuevo() {
        return nuevo;
    }
    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }



}
