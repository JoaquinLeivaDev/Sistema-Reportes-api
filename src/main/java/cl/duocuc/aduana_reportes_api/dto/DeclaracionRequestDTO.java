// DeclaracionRequestDTO.java
package cl.duocuc.aduana_reportes_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DeclaracionRequestDTO {

    @NotBlank(message = "El tipo de declaración es obligatorio")
    @Size(max = 50)
    private String tipo;

    @NotBlank(message = "Los detalles son obligatorios")
    private String detalles;

    @NotNull(message = "El id del pasajero es obligatorio")
    private Long idPasajero;
}