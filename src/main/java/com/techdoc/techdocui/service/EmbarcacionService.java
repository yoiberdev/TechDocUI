package com.techdoc.techdocui.service;

import com.techdoc.techdocui.dto.ApiResponseDto;
import com.techdoc.techdocui.models.Embarcacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmbarcacionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base.url:https://techdoc.yoiber.com/api}")
    private String apiBaseUrl;

    private final String embarcacionEndpoint = "/embarcaciones";

    public List<Embarcacion> obtenerTodasLasEmbarcaciones() {
        String url = apiBaseUrl + embarcacionEndpoint;
        ResponseEntity<ApiResponseDto<List<Embarcacion>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<List<Embarcacion>>>() {}
        );
        return response.getBody().getData();
    }

    public Embarcacion obtenerEmbarcacionPorId(Long id) {
        String url = apiBaseUrl + embarcacionEndpoint + "/" + id;
        ResponseEntity<ApiResponseDto<Embarcacion>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<Embarcacion>>() {}
        );
        return response.getBody().getData();
    }

    public Embarcacion crearEmbarcacion(Embarcacion embarcacion) {
        String url = apiBaseUrl + embarcacionEndpoint;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Embarcacion> requestEntity = new HttpEntity<>(embarcacion, headers);
        ResponseEntity<ApiResponseDto<Embarcacion>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<ApiResponseDto<Embarcacion>>() {}
        );
        return response.getBody().getData();
    }

    public void actualizarEmbarcacion(Long id, Embarcacion embarcacion) {
        String url = apiBaseUrl + embarcacionEndpoint + "/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Embarcacion> requestEntity = new HttpEntity<>(embarcacion, headers);
        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<ApiResponseDto<Void>>() {}
        );
    }

    public void eliminarEmbarcacion(Long id) {
        String url = apiBaseUrl + embarcacionEndpoint + "/" + id;
        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<ApiResponseDto<Void>>() {}
        );
    }

    public List<Embarcacion> buscarEmbarcacionesPorNombre(String nombre) {
        String url = apiBaseUrl + embarcacionEndpoint + "/buscar?nombre=" + nombre;
        ResponseEntity<ApiResponseDto<List<Embarcacion>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<List<Embarcacion>>>() {}
        );
        return response.getBody().getData();
    }
}