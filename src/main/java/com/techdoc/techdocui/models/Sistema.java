package com.techdoc.techdocui.models;

public class Sistema {
    private Long id;
    private String nombre;
    private String numeroSerie;
    private String ubicacionEnEmbarcacion;
    private String estado;

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }

    public String getUbicacionEnEmbarcacion() { return ubicacionEnEmbarcacion; }
    public void setUbicacionEnEmbarcacion(String ubicacionEnEmbarcacion) { this.ubicacionEnEmbarcacion = ubicacionEnEmbarcacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
