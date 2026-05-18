// DeclaracionResponseDTO.java
package cl.duocuc.aduana_reportes_api.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DeclaracionResponseDTO {
    private Long id;
    private String tipo;
    private String detalles;
    private Long idPasajero;
    private String nombrePasajero;
}