package com.techdoc.techdocui.controllers;

import com.techdoc.techdocui.models.Sistema;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Controller
public class SistemaController {

    @GetMapping("/sistemas")
    public String listarSistemas(Model model) {
        String apiUrl = "https://techdoc.yoiber.com/api/sistemas";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Sistema[]> response = restTemplate.getForEntity(apiUrl, Sistema[].class);
            model.addAttribute("sistemas", response.getBody());
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo conectar con la API.");
        }

        return "sistemas"; // Renderiza templates/sistemas.html
    }
}
