package com.techdoc.techdocui.controllers;

import com.techdoc.techdocui.dto.CreateSistemaRequest;
import com.techdoc.techdocui.dto.PaginatedResponse;
import com.techdoc.techdocui.dto.UpdateSistemaRequest;
import com.techdoc.techdocui.models.Sistema;
import com.techdoc.techdocui.service.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/sistemas")
public class SistemaController {

    @Autowired
    private SistemaService sistemaService;

    @GetMapping
    public String listarSistemasPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            Model model) {
        try {
            PaginatedResponse<Sistema> response = sistemaService.obtenerSistemasPaginados(page, size, sortBy, direction);
            model.addAttribute("sistemas", response.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", response.getTotalPages());
            model.addAttribute("totalElements", response.getTotalElements());
            model.addAttribute("pageSize", size);
            return "sistemas/list-paginated";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar los sistemas: " + e.getMessage());
            return "sistemas/list-paginated";
        }
    }

    // 🚨 Cambiado para evitar colisión
    @GetMapping("/detalle/{id}")
    public String verSistema(@PathVariable Long id, Model model) {
        try {
            Sistema sistema = sistemaService.obtenerSistemaPorId(id);
            model.addAttribute("sistema", sistema);
            return "sistemas/detail";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el sistema: " + e.getMessage());
            return "sistemas/detail";
        }
    }
    @ModelAttribute("sistema")
    public CreateSistemaRequest sistemaForm() {
        return new CreateSistemaRequest();
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        CreateSistemaRequest request = new CreateSistemaRequest();
        System.out.println("DEBUG: creando CreateSistemaRequest vacío");
        model.addAttribute("sistema", request);
        return "sistemas/form-create";
    }


    @PostMapping
    public String crearSistema(@ModelAttribute CreateSistemaRequest request,
                               RedirectAttributes redirectAttributes) {
        try {
            Sistema sistema = sistemaService.crearSistema(request);
            redirectAttributes.addFlashAttribute("mensaje", "Sistema creado exitosamente");
            return "redirect:/sistemas/detalle/" + sistema.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el sistema: " + e.getMessage());
            return "redirect:/sistemas/nuevo";
        }
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        try {
            Sistema sistema = sistemaService.obtenerSistemaPorId(id);
            UpdateSistemaRequest request = new UpdateSistemaRequest();
            request.setNombre(sistema.getNombre());
            request.setNumeroSerie(sistema.getNumeroSerie());
            request.setUbicacionEnEmbarcacion(sistema.getUbicacionEnEmbarcacion());
            request.setEstado(sistema.getEstado());
            request.setIdEmbarcacion(sistema.getIdEmbarcacion());
            request.setIdTipoSistema(sistema.getIdTipoSistema());
            request.setFechaInstalacion(sistema.getFechaInstalacion());
            request.setFechaUltimaRevision(sistema.getFechaUltimaRevision());
            request.setFechaProximoMantenimiento(sistema.getFechaProximoMantenimiento());
            request.setTiempoVidaRestante(sistema.getTiempoVidaRestante());
            request.setTecnicoInstalador(sistema.getTecnicoInstalador());
            request.setNotasInstalacion(sistema.getNotasInstalacion());
            request.setDiagramaUbicacion(sistema.getDiagramaUbicacion());

            model.addAttribute("sistema", request);
            model.addAttribute("sistemaId", id);
            return "sistemas/form-edit";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el sistema: " + e.getMessage());
            return "sistemas/list-paginated";
        }
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarSistema(@PathVariable Long id,
                                    @ModelAttribute UpdateSistemaRequest request,
                                    RedirectAttributes redirectAttributes) {
        try {
            sistemaService.actualizarSistema(id, request);
            redirectAttributes.addFlashAttribute("mensaje", "Sistema actualizado exitosamente");
            return "redirect:/sistemas/detalle/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el sistema: " + e.getMessage());
            return "redirect:/sistemas/" + id + "/editar";
        }
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarSistema(@PathVariable Long id,
                                  RedirectAttributes redirectAttributes) {
        try {
            sistemaService.eliminarSistema(id);
            redirectAttributes.addFlashAttribute("mensaje", "Sistema eliminado exitosamente");
            return "redirect:/sistemas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el sistema: " + e.getMessage());
            return "redirect:/sistemas/detalle/" + id;
        }
    }

    @GetMapping("/embarcacion/{idEmbarcacion}")
    public String listarSistemasPorEmbarcacion(@PathVariable Long idEmbarcacion, Model model) {
        try {
            List<Sistema> sistemas = sistemaService.obtenerSistemasPorEmbarcacion(idEmbarcacion);
            model.addAttribute("sistemas", sistemas);
            model.addAttribute("criterio", "Embarcación ID: " + idEmbarcacion);
            return "sistemas/list-filtered";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar los sistemas: " + e.getMessage());
            return "sistemas/list-filtered";
        }
    }

    @GetMapping("/tipo/{idTipoSistema}")
    public String listarSistemasPorTipo(@PathVariable Long idTipoSistema, Model model) {
        try {
            List<Sistema> sistemas = sistemaService.obtenerSistemasPorTipoSistema(idTipoSistema);
            model.addAttribute("sistemas", sistemas);
            model.addAttribute("criterio", "Tipo de Sistema ID: " + idTipoSistema);
            return "sistemas/list-filtered";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar los sistemas: " + e.getMessage());
            return "sistemas/list-filtered";
        }
    }

    @GetMapping("/estado/{estado}")
    public String listarSistemasPorEstado(@PathVariable String estado, Model model) {
        try {
            List<Sistema> sistemas = sistemaService.obtenerSistemasPorEstado(estado);
            model.addAttribute("sistemas", sistemas);
            model.addAttribute("criterio", "Estado: " + estado);
            return "sistemas/list-filtered";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar los sistemas: " + e.getMessage());
            return "sistemas/list-filtered";
        }
    }

    @GetMapping("/mantenimiento-requerido")
    public String listarSistemasQueRequierenMantenimiento(Model model) {
        try {
            List<Sistema> sistemas = sistemaService.obtenerSistemasQueRequierenMantenimiento();
            model.addAttribute("sistemas", sistemas);
            model.addAttribute("criterio", "Sistemas que requieren mantenimiento");
            return "sistemas/list-filtered";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar los sistemas: " + e.getMessage());
            return "sistemas/list-filtered";
        }
    }

    @GetMapping("/buscar")
    public String buscarSistemasPorNombre(@RequestParam String nombre, Model model) {
        try {
            List<Sistema> sistemas = sistemaService.obtenerSistemasPorNombre(nombre);
            model.addAttribute("sistemas", sistemas);
            model.addAttribute("criterio", "Nombre contiene: " + nombre);
            return "sistemas/list-filtered";
        } catch (Exception e) {
            model.addAttribute("error", "Error al buscar sistemas: " + e.getMessage());
            return "sistemas/list-filtered";
        }
    }
}
