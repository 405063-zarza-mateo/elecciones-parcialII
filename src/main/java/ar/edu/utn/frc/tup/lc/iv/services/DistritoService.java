package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.CargosDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.DistritoDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.SeccionDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.CustomException;
import ar.edu.utn.frc.tup.lc.iv.models.Cargo;
import ar.edu.utn.frc.tup.lc.iv.models.Distrito;
import ar.edu.utn.frc.tup.lc.iv.repository.DistritoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public CargosDto[] getCargos(Long distritoId, Long cargoId) {
        try {

            String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/cargos")
                .queryParam("distritoId", distritoId)
                .queryParam("cargoId", cargoId)
                .toUriString();

        CargosDto[] cargosResponse = restTemplate.getForEntity(url, CargosDto[].class).getBody();

        for (CargosDto cargosDto : cargosResponse) {
//            Distrito distrito = distritoRepository.findById(cargosDto.getDistritoId()).orElse(new Distrito());
            DistritoDto distrito = getDistritos(cargosDto.getDistritoId(), null).get(0);

            cargosDto.setDistrito(distrito);
        }

        return cargosResponse;
        } catch (
                RestClientException e) {
            throw new CustomException("Error al obtener los cargos:" + e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    public SeccionDto[] getSecciones(Long id){
        try {

            String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/secciones")
                    .queryParam("distritoNombre", "")
                    .queryParam("distritoId", id)
                    .toUriString();

            SeccionDto[] seccionResponse = restTemplate.getForEntity(url, SeccionDto[].class).getBody();

            for (SeccionDto seccionDto : seccionResponse) {
                seccionDto.setDistritoNombre(getDistritos(seccionDto.getDistritoId(), "").get(0).getNombre());
            }

            return seccionResponse;
        } catch (
                RestClientException e) {
            throw new CustomException("Error al obtener los cargos:" + e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}