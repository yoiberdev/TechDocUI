package com.techdoc.techdocui.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Embarcacion {
    private Long id;
    private String nombre;
    private String matricula;
    private String tipoEmbarcacion;
    private String empresaPropietaria;
    private Double capacidadCarga;
    private LocalDate fechaConstruccion;
    private String estado;
    private String ubicacionActual;
    private String notas;
    private LocalDateTime fechaRegistro;

    // Constructores
    public Embarcacion() {}

    public Embarcacion(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters and Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    public String getEmpresaPropietaria() {
        return empresaPropietaria;
    }

    public void setEmpresaPropietaria(String empresaPropietaria) {
        this.empresaPropietaria = empresaPropietaria;
    }

    public Double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(Double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public LocalDate getFechaConstruccion() {
        return fechaConstruccion;
    }

    public void setFechaConstruccion(LocalDate fechaConstruccion) {
        this.fechaConstruccion = fechaConstruccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}