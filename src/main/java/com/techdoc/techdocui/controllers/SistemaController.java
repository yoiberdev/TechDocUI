package com.techdoc.techdocui.controllers;

import com.techdoc.techdocui.models.Sistema;
import com.techdoc.techdocui.service.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
public class SistemaController {

    private static final Logger logger = LoggerFactory.getLogger(SistemaController.class);

    @Autowired
    private SistemaService sistemaService;

    @GetMapping("/")
    public String home() {
        return "redirect:/sistemas";
    }

    @GetMapping("/sistemas")
    public String listarSistemas(Model model) {
        try {
            List<Sistema> sistemas = sistemaService.obtenerTodosLosSistemas();
            model.addAttribute("sistemas", sistemas);
            logger.info("Mostrando {} sistemas", sistemas.size());
        } catch (Exception e) {
            logger.error("Error al cargar sistemas", e);
            model.addAttribute("error", "No se pudo conectar con la API: " + e.getMessage());
            model.addAttribute("sistemas", List.of());
        }

        return "sistemas"; // Renderiza templates/sistemas.html
    }

    @GetMapping("/sistemas/{id}")
    public String verDetallesSistema(@PathVariable Long id, Model model) {
        try {
            Sistema sistema = sistemaService.obtenerSistemaPorId(id);
            model.addAttribute("sistema", sistema);
            return "sistema-detalle";
        } catch (Exception e) {
            logger.error("Error al cargar el sistema con ID: {}", id, e);
            model.addAttribute("error", "No se pudo cargar el sistema");
            return "redirect:/sistemas";
        }
    }
}