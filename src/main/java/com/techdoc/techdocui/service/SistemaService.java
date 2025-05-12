package com.techdoc.techdocui.service;

import com.techdoc.techdocui.dto.ApiResponseDto;
import com.techdoc.techdocui.models.Sistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class SistemaService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base.url:https://techdoc.yoiber.com/api}")
    private String apiBaseUrl;

    public List<Sistema> obtenerTodosLosSistemas() {
        String url = apiBaseUrl + "/sistemas";
        try {
            ResponseEntity<ApiResponseDto<List<Sistema>>> response =
                    restTemplate.exchange(url,
                            org.springframework.http.HttpMethod.GET,
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
                            org.springframework.http.HttpMethod.GET,
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
}
