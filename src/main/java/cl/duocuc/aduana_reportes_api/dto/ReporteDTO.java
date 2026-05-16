package cl.duocuc.aduana_reportes_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReporteDTO {

    @NotBlank(message = "El tipo de reporte no puede estar vacío")
    private String tipo;

    @NotBlank(message = "Los datos del reporte son obligatorios")
    private String datos;

    @NotNull(message = "El ID del usuario que genera el reporte es obligatorio")
    private Long idUsuario;
}