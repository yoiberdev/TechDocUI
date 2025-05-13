package com.techdoc.techdocui.dto;

import java.time.LocalDate;

public class SistemaResponseCompletoDto {
    private Long id;
    private Long idEmbarcacion;
    private Long idTipoSistema;
    private String nombre;
    private String numeroSerie;
    private String ubicacionEnEmbarcacion;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmbarcacion() {
        return idEmbarcacion;
    }

    public void setIdEmbarcacion(Long idEmbarcacion) {
        this.idEmbarcacion = idEmbarcacion;
    }

    public Long getIdTipoSistema() {
        return idTipoSistema;
    }

    public void setIdTipoSistema(Long idTipoSistema) {
        this.idTipoSistema = idTipoSistema;
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

    public LocalDate getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(LocalDate fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public LocalDate getFechaUltimaRevision() {
        return fechaUltimaRevision;
    }

    public void setFechaUltimaRevision(LocalDate fechaUltimaRevision) {
        this.fechaUltimaRevision = fechaUltimaRevision;
    }

    public LocalDate getFechaProximoMantenimiento() {
        return fechaProximoMantenimiento;
    }

    public void setFechaProximoMantenimiento(LocalDate fechaProximoMantenimiento) {
        this.fechaProximoMantenimiento = fechaProximoMantenimiento;
    }

    public Integer getTiempoVidaRestante() {
        return tiempoVidaRestante;
    }

    public void setTiempoVidaRestante(Integer tiempoVidaRestante) {
        this.tiempoVidaRestante = tiempoVidaRestante;
    }

    public String getTecnicoInstalador() {
        return tecnicoInstalador;
    }

    public void setTecnicoInstalador(String tecnicoInstalador) {
        this.tecnicoInstalador = tecnicoInstalador;
    }

    public String getDiagramaUbicacion() {
        return diagramaUbicacion;
    }

    public void setDiagramaUbicacion(String diagramaUbicacion) {
        this.diagramaUbicacion = diagramaUbicacion;
    }

    public String getNotasInstalacion() {
        return notasInstalacion;
    }

    public void setNotasInstalacion(String notasInstalacion) {
        this.notasInstalacion = notasInstalacion;
    }

    public String getNombreEmbarcacion() {
        return nombreEmbarcacion;
    }

    public void setNombreEmbarcacion(String nombreEmbarcacion) {
        this.nombreEmbarcacion = nombreEmbarcacion;
    }

    public String getNombreTipoSistema() {
        return nombreTipoSistema;
    }

    public void setNombreTipoSistema(String nombreTipoSistema) {
        this.nombreTipoSistema = nombreTipoSistema;
    }

    private String estado;

    private LocalDate fechaInstalacion;
    private LocalDate fechaUltimaRevision;
    private LocalDate fechaProximoMantenimiento;
    private Integer tiempoVidaRestante;
    private String tecnicoInstalador;
    private String notasInstalacion;
    private String diagramaUbicacion;

    private String nombreEmbarcacion;
    private String nombreTipoSistema;
}
