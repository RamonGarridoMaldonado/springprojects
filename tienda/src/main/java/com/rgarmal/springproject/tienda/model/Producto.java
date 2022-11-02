package com.rgarmal.springproject.tienda.model;

import java.util.Date;

public class Producto {
    
    private int codigo;
    private String nombre;
    private String descripcion;
    private String urlImg;
    private Date fecha;

    public Producto() {
    }

    public Producto(int codigo) {
        this.codigo = codigo;
    }

    public Producto(int codigo, String nombre,String descripcion, String urlImg) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlImg = urlImg;
    }

    public Producto(int codigo, String nombre, String descripcion, String urlImg, Date fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlImg = urlImg;
        this.fecha = fecha;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getUrlImg() {
        return urlImg;
    }
    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}
