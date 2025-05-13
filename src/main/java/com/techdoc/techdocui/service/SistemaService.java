package com.techdoc.techdocui.service;

import com.techdoc.techdocui.dto.*;
import com.techdoc.techdocui.models.Sistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class SistemaService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base.url:https://techdoc.yoiber.com/api}")
    private String apiBaseUrl;

    // Métodos existentes mejorados
    public List<Sistema> obtenerTodosLosSistemas() {
        String url = apiBaseUrl + "/sistemas";
        try {
            ResponseEntity<ApiResponseDto<List<Sistema>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Sistema>>>() {}
                    );

            ApiResponseDto<List<Sistema>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener los sistemas: " + e.getMessage(), e);
        }
    }

    public Sistema obtenerSistemaPorId(Long id) {
        String url = apiBaseUrl + "/sistemas/" + id;
        try {
            ResponseEntity<ApiResponseDto<Sistema>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<Sistema>>() {}
                    );

            ApiResponseDto<Sistema> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess()) {
                return apiResponse.getData();
            }
            throw new RuntimeException("No se encontró el sistema");
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener el sistema: " + e.getMessage(), e);
        }
    }

    // Nuevo método para obtener sistemas paginados
    public PaginatedResponse<Sistema> obtenerSistemasPaginados(int page, int size, String sortBy, String direction) {
        String url = UriComponentsBuilder.fromUriString(apiBaseUrl + "/sistemas/paged")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sortBy", sortBy)
                .queryParam("direction", direction)
                .build()
                .toUriString();

        try {
            ResponseEntity<ApiResponseDto<PaginatedResponse<Sistema>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<PaginatedResponse<Sistema>>>() {}
                    );

            ApiResponseDto<PaginatedResponse<Sistema>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return new PaginatedResponse<>();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener los sistemas paginados: " + e.getMessage(), e);
        }
    }

    // Crear nuevo sistema
    public Sistema crearSistema(CreateSistemaRequest request) {
        String url = apiBaseUrl + "/sistemas";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<CreateSistemaRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<ApiResponseDto<Sistema>> response =
                    restTemplate.exchange(url,
                            HttpMethod.POST,
                            entity,
                            new ParameterizedTypeReference<ApiResponseDto<Sistema>>() {}
                    );

            ApiResponseDto<Sistema> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess()) {
                return apiResponse.getData();
            }
            throw new RuntimeException("Error al crear el sistema");
        } catch (RestClientException e) {
            throw new RuntimeException("Error al crear el sistema: " + e.getMessage(), e);
        }
    }

    // Actualizar sistema existente
    public Sistema actualizarSistema(Long id, UpdateSistemaRequest request) {
        String url = apiBaseUrl + "/sistemas/" + id;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UpdateSistemaRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<ApiResponseDto<Sistema>> response =
                    restTemplate.exchange(url,
                            HttpMethod.PUT,
                            entity,
                            new ParameterizedTypeReference<ApiResponseDto<Sistema>>() {}
                    );

            ApiResponseDto<Sistema> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess()) {
                return apiResponse.getData();
            }
            throw new RuntimeException("Error al actualizar el sistema");
        } catch (RestClientException e) {
            throw new RuntimeException("Error al actualizar el sistema: " + e.getMessage(), e);
        }
    }

    // Eliminar sistema
    public void eliminarSistema(Long id) {
        String url = apiBaseUrl + "/sistemas/" + id;
        try {
            ResponseEntity<ApiResponseDto<Void>> response =
                    restTemplate.exchange(url,
                            HttpMethod.DELETE,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<Void>>() {}
                    );

            ApiResponseDto<Void> apiResponse = response.getBody();
            if (apiResponse == null || !apiResponse.isSuccess()) {
                throw new RuntimeException("Error al eliminar el sistema");
            }
        } catch (RestClientException e) {
            throw new RuntimeException("Error al eliminar el sistema: " + e.getMessage(), e);
        }
    }

    // Buscar sistemas por embarcación
    public List<Sistema> obtenerSistemasPorEmbarcacion(Long idEmbarcacion) {
        String url = apiBaseUrl + "/sistemas/buscar/embarcacion/" + idEmbarcacion;
        try {
            ResponseEntity<ApiResponseDto<List<Sistema>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Sistema>>>() {}
                    );

            ApiResponseDto<List<Sistema>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener sistemas por embarcación: " + e.getMessage(), e);
        }
    }

    // Buscar sistemas por tipo de sistema
    public List<Sistema> obtenerSistemasPorTipoSistema(Long idTipoSistema) {
        String url = apiBaseUrl + "/sistemas/buscar/tipo-sistema/" + idTipoSistema;
        try {
            ResponseEntity<ApiResponseDto<List<Sistema>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Sistema>>>() {}
                    );

            ApiResponseDto<List<Sistema>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener sistemas por tipo: " + e.getMessage(), e);
        }
    }

    // Buscar sistemas por estado
    public List<Sistema> obtenerSistemasPorEstado(String estado) {
        String url = apiBaseUrl + "/sistemas/buscar/estado/" + estado;
        try {
            ResponseEntity<ApiResponseDto<List<Sistema>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Sistema>>>() {}
                    );

            ApiResponseDto<List<Sistema>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener sistemas por estado: " + e.getMessage(), e);
        }
    }

    // Obtener sistemas que requieren mantenimiento
    public List<Sistema> obtenerSistemasQueRequierenMantenimiento() {
        String url = apiBaseUrl + "/sistemas/buscar/mantenimiento-requerido";
        try {
            ResponseEntity<ApiResponseDto<List<Sistema>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Sistema>>>() {}
                    );

            ApiResponseDto<List<Sistema>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener sistemas que requieren mantenimiento: " + e.getMessage(), e);
        }
    }

    // Buscar sistemas por nombre
    public List<Sistema> obtenerSistemasPorNombre(String nombre) {
        String url = apiBaseUrl + "/sistemas/buscar/nombre/" + nombre;
        try {
            ResponseEntity<ApiResponseDto<List<Sistema>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Sistema>>>() {}
                    );

            ApiResponseDto<List<Sistema>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener sistemas por nombre: " + e.getMessage(), e);
        }
    }
}