package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.CargosDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.DistritoDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.SeccionDto;
import ar.edu.utn.frc.tup.lc.iv.services.DistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class distritoController {

    @Autowired
    DistritoService distritoService;

    @GetMapping("/distritos")
    List<DistritoDto> getDistritos(@RequestParam(value = "distritoId", required = false) Long id,
                                   @RequestParam(value = "distritoNombre", required = false) String nombre) {
        return distritoService.getDistritos(id, nombre);
    }

    @GetMapping("/cargos")
    CargosDto[] getCargos(@RequestParam(value = "distritoId", required = false) Long distritoId,
                           @RequestParam(value = "cargoId", required = false) Long cargoId) {
        return distritoService.getCargos(distritoId, cargoId);
    }

    @GetMapping("/secciones")
    SeccionDto[] getSecciones(@RequestParam(value = "distritoId") Long id){
        return distritoService.getSecciones(id);
    }
}
