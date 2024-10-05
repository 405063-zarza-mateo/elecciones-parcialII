package ar.edu.utn.frc.tup.lc.iv.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistritoDto {
    @JsonProperty("distritoId")
    Long id;
    @JsonProperty("distritoNombre")
    String nombre;
}
