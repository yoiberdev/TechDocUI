package com.techdoc.techdocui.service;

import com.techdoc.techdocui.models.TipoSistema;
import com.techdoc.techdocui.dto.ApiResponseDto;
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
public class TipoSistemaService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base.url=https://techdoc.yoiber.com/api}")
    private String apiBaseUrl;

    private final String tipoSistemaEndpoint = "/tipos-sistema";

    public List<TipoSistema> obtenerTodosTiposSistema() {
        String url = apiBaseUrl + tipoSistemaEndpoint;
        ResponseEntity<ApiResponseDto<List<TipoSistema>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<List<TipoSistema>>>() {}
        );
        return response.getBody().getData();
    }

    public TipoSistema obtenerTipoSistemaPorId(Long id) {
        String url = apiBaseUrl + tipoSistemaEndpoint + "/" + id;
        ResponseEntity<ApiResponseDto<TipoSistema>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<TipoSistema>>() {}
        );
        return response.getBody().getData();
    }

    public TipoSistema crearTipoSistema(TipoSistema tipoSistema) {
        String url = apiBaseUrl + tipoSistemaEndpoint;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<TipoSistema> requestEntity = new HttpEntity<>(tipoSistema, headers);
        ResponseEntity<ApiResponseDto<TipoSistema>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<ApiResponseDto<TipoSistema>>() {}
        );
        return response.getBody().getData();
    }

    public void actualizarTipoSistema(Long id, TipoSistema tipoSistema) {
        String url = apiBaseUrl + tipoSistemaEndpoint + "/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<TipoSistema> requestEntity = new HttpEntity<>(tipoSistema, headers);
        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<ApiResponseDto<Void>>() {}
        );
    }

    public void eliminarTipoSistema(Long id) {
        String url = apiBaseUrl + tipoSistemaEndpoint + "/" + id;
        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<ApiResponseDto<Void>>() {}
        );
    }

    public List<TipoSistema> buscarTipoSistemaPorNombre(String nombre) {
        String url = apiBaseUrl + tipoSistemaEndpoint + "/buscar?nombre=" + nombre;
        ResponseEntity<ApiResponseDto<List<TipoSistema>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<List<TipoSistema>>>() {}
        );
        return response.getBody().getData();
    }

    public List<TipoSistema> obtenerTipoSistemaPorCategoria(String categoria) {
        String url = apiBaseUrl + tipoSistemaEndpoint + "/categoria/" + categoria;
        ResponseEntity<ApiResponseDto<List<TipoSistema>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<List<TipoSistema>>>() {}
        );
        return response.getBody().getData();
    }
}