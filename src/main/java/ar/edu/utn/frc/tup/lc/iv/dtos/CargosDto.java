package ar.edu.utn.frc.tup.lc.iv.dtos;

import ar.edu.utn.frc.tup.lc.iv.models.Cargo;
import ar.edu.utn.frc.tup.lc.iv.models.Distrito;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargosDto {
    @JsonProperty("cargoId")
    Long cargoId;
    @JsonProperty("cargoNombre")
    String nombre;
    @JsonProperty("distritoId")
    Long distritoId;
    @JsonProperty("Distrito")
    DistritoDto distrito;

}
