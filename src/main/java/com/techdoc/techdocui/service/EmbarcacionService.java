package com.techdoc.techdocui.service;

import com.techdoc.techdocui.dto.*;
import com.techdoc.techdocui.models.Embarcacion;
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
public class EmbarcacionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base.url:https://techdoc.yoiber.com/api}")
    private String apiBaseUrl;

    private final String embarcacionEndpoint = "/embarcaciones";

    public List<Embarcacion> obtenerTodasLasEmbarcaciones() {
        String url = apiBaseUrl + embarcacionEndpoint;
        try {
            ResponseEntity<ApiResponseDto<List<Embarcacion>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Embarcacion>>>() {}
                    );

            ApiResponseDto<List<Embarcacion>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener las embarcaciones: " + e.getMessage(), e);
        }
    }

    public Embarcacion obtenerEmbarcacionPorId(Long id) {
        String url = apiBaseUrl + embarcacionEndpoint + "/" + id;
        try {
            ResponseEntity<ApiResponseDto<Embarcacion>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<Embarcacion>>() {}
                    );

            ApiResponseDto<Embarcacion> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess()) {
                return apiResponse.getData();
            }
            throw new RuntimeException("No se encontró la embarcación");
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener la embarcación: " + e.getMessage(), e);
        }
    }

    // Método para obtener embarcaciones paginadas
    public PaginatedResponse<Embarcacion> obtenerEmbarcacionesPaginadas(int page, int size, String sortBy, String direction) {
        String url = UriComponentsBuilder.fromUriString(apiBaseUrl + embarcacionEndpoint + "/paged")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sortBy", sortBy)
                .queryParam("direction", direction)
                .build()
                .toUriString();

        try {
            ResponseEntity<ApiResponseDto<PaginatedResponse<Embarcacion>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<PaginatedResponse<Embarcacion>>>() {}
                    );

            ApiResponseDto<PaginatedResponse<Embarcacion>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return new PaginatedResponse<>();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener las embarcaciones paginadas: " + e.getMessage(), e);
        }
    }

    // Crear nueva embarcación
    public Embarcacion crearEmbarcacion(CreateEmbarcacionRequest request) {
        String url = apiBaseUrl + embarcacionEndpoint;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<CreateEmbarcacionRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<ApiResponseDto<Embarcacion>> response =
                    restTemplate.exchange(url,
                            HttpMethod.POST,
                            entity,
                            new ParameterizedTypeReference<ApiResponseDto<Embarcacion>>() {}
                    );

            ApiResponseDto<Embarcacion> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess()) {
                return apiResponse.getData();
            }
            throw new RuntimeException("Error al crear la embarcación");
        } catch (RestClientException e) {
            throw new RuntimeException("Error al crear la embarcación: " + e.getMessage(), e);
        }
    }

    // Actualizar embarcación existente
    public Embarcacion actualizarEmbarcacion(Long id, UpdateEmbarcacionRequest request) {
        String url = apiBaseUrl + embarcacionEndpoint + "/" + id;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UpdateEmbarcacionRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<ApiResponseDto<Embarcacion>> response =
                    restTemplate.exchange(url,
                            HttpMethod.PUT,
                            entity,
                            new ParameterizedTypeReference<ApiResponseDto<Embarcacion>>() {}
                    );

            ApiResponseDto<Embarcacion> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess()) {
                return apiResponse.getData();
            }
            throw new RuntimeException("Error al actualizar la embarcación");
        } catch (RestClientException e) {
            throw new RuntimeException("Error al actualizar la embarcación: " + e.getMessage(), e);
        }
    }

    // Eliminar embarcación
    public void eliminarEmbarcacion(Long id) {
        String url = apiBaseUrl + embarcacionEndpoint + "/" + id;
        try {
            ResponseEntity<ApiResponseDto<Void>> response =
                    restTemplate.exchange(url,
                            HttpMethod.DELETE,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<Void>>() {}
                    );

            ApiResponseDto<Void> apiResponse = response.getBody();
            if (apiResponse == null || !apiResponse.isSuccess()) {
                throw new RuntimeException("Error al eliminar la embarcación");
            }
        } catch (RestClientException e) {
            throw new RuntimeException("Error al eliminar la embarcación: " + e.getMessage(), e);
        }
    }

    // Buscar embarcaciones por nombre
    public List<Embarcacion> buscarEmbarcacionesPorNombre(String nombre) {
        String url = UriComponentsBuilder.fromUriString(apiBaseUrl + embarcacionEndpoint + "/buscar/nombre")
                .queryParam("nombre", nombre)
                .build()
                .toUriString();
        try {
            ResponseEntity<ApiResponseDto<List<Embarcacion>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Embarcacion>>>() {}
                    );

            ApiResponseDto<List<Embarcacion>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al buscar embarcaciones por nombre: " + e.getMessage(), e);
        }
    }

    // Buscar embarcaciones por tipo
    public List<Embarcacion> buscarEmbarcacionesPorTipo(String tipo) {
        String url = apiBaseUrl + embarcacionEndpoint + "/buscar/tipo/" + tipo;
        try {
            ResponseEntity<ApiResponseDto<List<Embarcacion>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Embarcacion>>>() {}
                    );

            ApiResponseDto<List<Embarcacion>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al buscar embarcaciones por tipo: " + e.getMessage(), e);
        }
    }

    // Buscar embarcaciones por estado
    public List<Embarcacion> buscarEmbarcacionesPorEstado(String estado) {
        String url = apiBaseUrl + embarcacionEndpoint + "/buscar/estado/" + estado;
        try {
            ResponseEntity<ApiResponseDto<List<Embarcacion>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Embarcacion>>>() {}
                    );

            ApiResponseDto<List<Embarcacion>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al buscar embarcaciones por estado: " + e.getMessage(), e);
        }
    }

    // Obtener embarcaciones que requieren mantenimiento
    public List<Embarcacion> obtenerEmbarcacionesQueRequierenMantenimiento() {
        String url = apiBaseUrl + embarcacionEndpoint + "/buscar/mantenimiento-requerido";
        try {
            ResponseEntity<ApiResponseDto<List<Embarcacion>>> response =
                    restTemplate.exchange(url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponseDto<List<Embarcacion>>>() {}
                    );

            ApiResponseDto<List<Embarcacion>> apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.isSuccess() && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
            return Collections.emptyList();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener embarcaciones que requieren mantenimiento: " + e.getMessage(), e);
        }
    }
}