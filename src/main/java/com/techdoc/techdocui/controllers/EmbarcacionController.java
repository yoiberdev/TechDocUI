package com.techdoc.techdocui.controllers;

import com.techdoc.techdocui.dto.CreateEmbarcacionRequest;
import com.techdoc.techdocui.dto.UpdateEmbarcacionRequest;
import com.techdoc.techdocui.dto.PaginatedResponse;
import com.techdoc.techdocui.models.Embarcacion;
import com.techdoc.techdocui.service.EmbarcacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/embarcaciones")
public class EmbarcacionController {

    @Autowired
    private EmbarcacionService embarcacionService;

    @GetMapping
    public String listarEmbarcacionesPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            Model model) {
        try {
            PaginatedResponse<Embarcacion> response = embarcacionService.obtenerEmbarcacionesPaginadas(page, size, sortBy, direction);
            model.addAttribute("embarcaciones", response.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", response.getTotalPages());
            model.addAttribute("totalElements", response.getTotalElements());
            model.addAttribute("pageSize", size);
            return "embarcaciones/list-paginated";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar las embarcaciones: " + e.getMessage());
            return "embarcaciones/list-paginated";
        }
    }

    @GetMapping("/detalle/{id}")
    public String verEmbarcacion(@PathVariable Long id, Model model) {
        try {
            Embarcacion embarcacion = embarcacionService.obtenerEmbarcacionPorId(id);
            model.addAttribute("embarcacion", embarcacion);
            return "embarcaciones/detail";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar la embarcación: " + e.getMessage());
            return "embarcaciones/detail";
        }
    }

    @ModelAttribute("embarcoForm")
    public CreateEmbarcacionRequest embarcacionForm() {
        return new CreateEmbarcacionRequest();
    }

    @ModelAttribute("editForm")
    public UpdateEmbarcacionRequest updateEmbarcacionForm() {
        return new UpdateEmbarcacionRequest();
    }

    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("embarcoForm", new CreateEmbarcacionRequest());
        return "embarcaciones/form-create";
    }

    @PostMapping
    public String crearEmbarcacion(@ModelAttribute("embarcoForm") CreateEmbarcacionRequest request,
                                   RedirectAttributes redirectAttributes) {
        try {
            Embarcacion embarcacion = embarcacionService.crearEmbarcacion(request);
            redirectAttributes.addFlashAttribute("mensaje", "Embarcación creada exitosamente");
            return "redirect:/embarcaciones/detalle/" + embarcacion.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear la embarcación: " + e.getMessage());
            return "redirect:/embarcaciones/nueva";
        }
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        try {
            Embarcacion embarcacion = embarcacionService.obtenerEmbarcacionPorId(id);
            UpdateEmbarcacionRequest request = new UpdateEmbarcacionRequest();

            request.setNombre(embarcacion.getNombre());
            request.setMatricula(embarcacion.getMatricula());
            request.setTipoEmbarcacion(embarcacion.getTipoEmbarcacion());
            request.setEmpresaPropietaria(embarcacion.getEmpresaPropietaria());
            request.setCapacidadCarga(embarcacion.getCapacidadCarga());
            request.setFechaConstruccion(embarcacion.getFechaConstruccion());
            request.setEstado(embarcacion.getEstado());
            request.setUbicacionActual(embarcacion.getUbicacionActual());
            request.setNotas(embarcacion.getNotas());
            request.setFechaRegistro(embarcacion.getFechaRegistro());

            model.addAttribute("editForm", request);
            model.addAttribute("embarcoId", id);
            return "embarcaciones/form-edit";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar la embarcación: " + e.getMessage());
            return "redirect:/embarcaciones";
        }
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarEmbarcacion(@PathVariable Long id,
                                        @ModelAttribute("editForm") UpdateEmbarcacionRequest request,
                                        RedirectAttributes redirectAttributes) {
        try {
            embarcacionService.actualizarEmbarcacion(id, request);
            redirectAttributes.addFlashAttribute("mensaje", "Embarcación actualizada exitosamente");
            return "redirect:/embarcaciones/detalle/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar la embarcación: " + e.getMessage());
            return "redirect:/embarcaciones/" + id + "/editar";
        }
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarEmbarcacion(@PathVariable Long id,
                                      RedirectAttributes redirectAttributes) {
        try {
            embarcacionService.eliminarEmbarcacion(id);
            redirectAttributes.addFlashAttribute("mensaje", "Embarcación eliminada exitosamente");
            return "redirect:/embarcaciones";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la embarcación: " + e.getMessage());
            return "redirect:/embarcaciones/detalle/" + id;
        }
    }

    @GetMapping("/estado/{estado}")
    public String listarPorEstado(@PathVariable String estado, Model model) {
        try {
            List<Embarcacion> embarcaciones = embarcacionService.buscarEmbarcacionesPorEstado(estado);
            model.addAttribute("embarcaciones", embarcaciones);
            model.addAttribute("criterio", "Estado: " + estado);
            return "embarcaciones/list-filtered";
        } catch (Exception e) {
            model.addAttribute("error", "Error al buscar embarcaciones: " + e.getMessage());
            return "embarcaciones/list-filtered";
        }
    }

    @GetMapping("/buscar")
    public String buscarPorNombre(@RequestParam String nombre, Model model) {
        try {
            List<Embarcacion> embarcaciones = embarcacionService.buscarEmbarcacionesPorNombre(nombre);
            model.addAttribute("embarcaciones", embarcaciones);
            model.addAttribute("criterio", "Nombre contiene: " + nombre);
            return "embarcaciones/list-filtered";
        } catch (Exception e) {
            model.addAttribute("error", "Error al buscar embarcaciones: " + e.getMessage());
            return "embarcaciones/list-filtered";
        }
    }
}
