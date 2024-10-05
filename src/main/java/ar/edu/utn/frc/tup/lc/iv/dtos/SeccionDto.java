package ar.edu.utn.frc.tup.lc.iv.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeccionDto {
    @JsonProperty("seccionId")
    Long id;
    @JsonProperty("seccionNombre")
    String nombre;
    @JsonProperty("distritoId")
    Long distritoId;
    @JsonProperty("distritoNombre")
    String distritoNombre;

}
