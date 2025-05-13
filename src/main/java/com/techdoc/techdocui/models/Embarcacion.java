package com.techdoc.techdocui.models;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Embarcacion {
    private Long id;
    private String nombre;

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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getContactoPropietario() {
        return contactoPropietario;
    }

    public void setContactoPropietario(String contactoPropietario) {
        this.contactoPropietario = contactoPropietario;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    private String matricula;
    private String tipo;
    private String modelo;
    private String propietario;
    private String contactoPropietario;
    private LocalDate fechaFabricacion;
    private String estado;
    private String ubicacionActual;
    private String notas;
    private String imagen;

    // Constructores
    public Embarcacion() {}

    public Embarcacion(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}