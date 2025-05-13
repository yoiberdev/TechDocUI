package com.techdoc.techdocui.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSistemaRequest {
    private String nombre;
    private String numeroSerie;
    private LocalDate fechaInstalacion;
    private LocalDate fechaUltimaRevision;
    private LocalDate fechaProximoMantenimiento;
    private Integer tiempoVidaRestante;
    private String tecnicoInstalador;
    private String notasInstalacion;
    private String diagramaUbicacion;
    private String ubicacionEnEmbarcacion;
    private String estado;
    private Long idEmbarcacion;
    private Long idTipoSistema;
}
