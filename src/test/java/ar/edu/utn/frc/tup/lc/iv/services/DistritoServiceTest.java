package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.models.Distrito;
import ar.edu.utn.frc.tup.lc.iv.repository.DistritoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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

    private DistritoService distritoService;
    Distrito[] distritos;
    ResponseEntity response;

    @BeforeEach
    void setUp() {
        Distrito distrito = new Distrito();
        distrito.setId(1);
        distrito.setNombre("Cordoba");
        distritos = List.of(distrito).toArray(new Distrito[0]);
    }


    @Test
    void getDistritos() {
//    doNothing().when(restTemplate).getForEntity(any(),any());
        doReturn(distritos).when(restTemplate).getForEntity(any(),any());
        distritoService.getDistritos(1l, "c");
    }

    @Test
    void getCargos() {
    }
}