package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.DistritoDto;
import ar.edu.utn.frc.tup.lc.iv.models.Distrito;
import ar.edu.utn.frc.tup.lc.iv.repository.DistritoRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class DistritoServiceTest {

    @Mock
    DistritoRepository distritoRepository;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private DistritoService distritoService;

    DistritoDto[] distritos;
    ResponseEntity response;

    @BeforeEach
    void setUp() {
        DistritoDto distrito = new DistritoDto(1l, "Cordoba");
        distritos = List.of(distrito).toArray(new DistritoDto[0]);
        response = new ResponseEntity(HttpStatus.ACCEPTED);
    }


    @Test
    void getDistritos() {
        Long id = 1L;
        String nombre = "Distrito1";

        when(restTemplate.getForEntity(anyString(), eq(DistritoDto[].class)))
                .thenReturn(ResponseEntity.ok(distritos));

        List<DistritoDto> result = distritoService.getDistritos(id, nombre);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restTemplate).getForEntity(anyString(), eq(DistritoDto[].class));

    }

    @Test
    void getDistritosNoResults() {
        Long id = 1L;
        String nombre = "Distrito1";

        when(restTemplate.getForEntity(anyString(), eq(DistritoDto[].class)))
                .thenReturn(ResponseEntity.ofNullable(null));

        List<DistritoDto> result = distritoService.getDistritos(id, nombre);

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(restTemplate).getForEntity(anyString(), eq(DistritoDto[].class));

    }

    @Test
    void getCargos() {
    }
}