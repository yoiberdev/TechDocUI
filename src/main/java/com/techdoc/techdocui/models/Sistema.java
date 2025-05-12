package com.techdoc.techdocui.models;

import java.time.LocalDateTime;

public class Sistema {
    private Long id;
    private String nombre;
    private String numeroSerie;
    private String ubicacionEnEmbarcacion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    // Constructor por defecto
    public Sistema() {}

    // Constructor con parámetros
    public Sistema(Long id, String nombre, String numeroSerie, String ubicacionEnEmbarcacion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.numeroSerie = numeroSerie;
        this.ubicacionEnEmbarcacion = ubicacionEnEmbarcacion;
        this.estado = estado;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getUbicacionEnEmbarcacion() {
        return ubicacionEnEmbarcacion;
    }

    public void setUbicacionEnEmbarcacion(String ubicacionEnEmbarcacion) {
        this.ubicacionEnEmbarcacion = ubicacionEnEmbarcacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    // Métodos de utilidad
    public boolean isActivo() {
        return "Activo".equalsIgnoreCase(this.estado);
    }

    public boolean isInactivo() {
        return "Inactivo".equalsIgnoreCase(this.estado);
    }

    public boolean isEnMantenimiento() {
        return "Mantenimiento".equalsIgnoreCase(this.estado);
    }

    @Override
    public String toString() {
        return "Sistema{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", ubicacionEnEmbarcacion='" + ubicacionEnEmbarcacion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}