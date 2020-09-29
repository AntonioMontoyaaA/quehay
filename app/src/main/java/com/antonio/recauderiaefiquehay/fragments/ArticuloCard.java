package com.antonio.recauderiaefiquehay.fragments;

public class ArticuloCard {

    private String nombre;
    private String descripcion;
    private String urlArchivo;
    private String unidadMedida;
    private int categoria;

    public ArticuloCard(String nombre, String descripcion, String urlArchivo, String unidadMedida, int categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlArchivo = urlArchivo;
        this.unidadMedida = unidadMedida;
        this.categoria = categoria;
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

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
