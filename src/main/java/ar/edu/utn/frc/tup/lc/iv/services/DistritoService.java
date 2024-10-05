package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.CargosDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.DistritoDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.CustomException;
import ar.edu.utn.frc.tup.lc.iv.repository.DistritoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class DistritoService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DistritoRepository distritoRepository;

    public List<DistritoDto> getDistritos(Long id, String nombre) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/distritos")
                    .queryParam("distritoId", id)
                    .queryParam("distritoNombre", nombre)
                    .toUriString();

            DistritoDto[] distritos = restTemplate.getForEntity(url, DistritoDto[].class).getBody();

            if (distritos == null || distritos.length == 0) {
                return List.of();
            }

            return Arrays.asList(distritos);
        } catch (
                RestClientException e) {
            throw new CustomException("Error al obtener los distritos:" + e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    public CargosDto getCargos(Long distritoId, Long cargoId) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/cargos")
                    .queryParam("distrito_id", distritoId)
                    .queryParam("cargo_id", cargoId)
                    .toUriString();


            CargosDto cargos = restTemplate.getForObject(url, CargosDto.class);

            if (cargos == null) {
                throw new CustomException("No se pudieron obtener los cargos", HttpStatus.NOT_FOUND);
            }

            return cargos;
        } catch (HttpClientErrorException e) {
            throw new CustomException("Error al obtener los cargos: " + e.getResponseBodyAsString(), (HttpStatus) e.getStatusCode());
        } catch (HttpServerErrorException e) {
            throw new CustomException("Error del servidor al obtener los cargos: " + e.getResponseBodyAsString(), (HttpStatus) e.getStatusCode());
        } catch (RestClientException e) {
            throw new CustomException("Error de conexión al obtener los cargos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}