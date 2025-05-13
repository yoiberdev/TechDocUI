package com.techdoc.techdocui.models;

import lombok.Data;

@Data
public class TipoSistema {
    private Long id;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getUnidadTiempo() {
        return unidadTiempo;
    }

    public void setUnidadTiempo(String unidadTiempo) {
        this.unidadTiempo = unidadTiempo;
    }

    public Integer getIntervaloMantenimiento() {
        return intervaloMantenimiento;
    }

    public void setIntervaloMantenimiento(Integer intervaloMantenimiento) {
        this.intervaloMantenimiento = intervaloMantenimiento;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getNotasTecnicas() {
        return notasTecnicas;
    }

    public void setNotasTecnicas(String notasTecnicas) {
        this.notasTecnicas = notasTecnicas;
    }

    private String nombre;
    private String descripcion;
    private String categoria;
    private Integer vidaUtilEstimada;
    private String unidadTiempo;
    private Integer intervaloMantenimiento;
    private String fabricante;
    private String notasTecnicas;

    // Constructores
    public TipoSistema() {}

    public TipoSistema(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}