package com.antonio.recauderiaefiquehay.fragments;

public class CardNuevo {

    private String urlArchivo;
    private String nombre;
    private String unidadMedida;
    private String descripcion;

    public CardNuevo(String urlArchivo, String nombre, String unidadMedida, String descripcion) {
        this.urlArchivo = urlArchivo;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.descripcion = descripcion;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
