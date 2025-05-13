package com.techdoc.techdocui.models;

import java.time.LocalDateTime;

public class Sistema {
    private Long id;
    private String nombre;
    private String numeroSerie;
    private String ubicacionEnEmbarcacion;
    private String estado;

    // 🔴 ESTO TE FALTA
    private Long idEmbarcacion;
    private Long idTipoSistema;

    // Getters y setters...

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

    public Long getIdEmbarcacion() { return idEmbarcacion; }
    public void setIdEmbarcacion(Long idEmbarcacion) { this.idEmbarcacion = idEmbarcacion; }

    public Long getIdTipoSistema() { return idTipoSistema; }
    public void setIdTipoSistema(Long idTipoSistema) { this.idTipoSistema = idTipoSistema; }
}
