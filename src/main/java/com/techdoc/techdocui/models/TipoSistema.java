package com.techdoc.techdocui.models;

import lombok.Data;

@Data
public class TipoSistema {
    private Long id;
    private String nombre;
    private String descripcion;
    private String fabricanteRecomendado;
    private String categoria;
    private Integer vidaUtilEstimada;


    // Constructores
    public TipoSistema() {}

    public TipoSistema(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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

    public String getFabricanteRecomendado() {
        return fabricanteRecomendado;
    }

    public void setFabricanteRecomendado(String fabricanteRecomendado) {
        this.fabricanteRecomendado = fabricanteRecomendado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getVidaUtilEstimada() {
        return vidaUtilEstimada;
    }

    public void setVidaUtilEstimada(Integer vidaUtilEstimada) {
        this.vidaUtilEstimada = vidaUtilEstimada;
    }
}