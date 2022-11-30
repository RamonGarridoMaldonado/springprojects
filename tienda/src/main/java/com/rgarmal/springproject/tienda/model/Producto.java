package com.rgarmal.springproject.tienda.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.rgarmal.springproject.tienda.utils.ImageUtil;

public class Producto {
    
    private int codigo;
    private String nombre;
    private String descripcion;
    private float precio;
    private byte[] imagen;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    public Producto() {
    }

    public Producto(int codigo) {
        this.codigo = codigo;
    }

    public Producto(int codigo, String nombre,String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Producto(int codigo, String nombre, String descripcion, Date fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImageView() {
        return ImageUtil.getImgData(this.imagen);
    }
}
