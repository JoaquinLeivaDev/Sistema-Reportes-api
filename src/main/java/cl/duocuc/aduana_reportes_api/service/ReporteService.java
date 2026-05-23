package cl.duocuc.aduana_reportes_api.service;

import cl.duocuc.aduana_reportes_api.client.AduanaClient;
import cl.duocuc.aduana_reportes_api.dto.*;
import cl.duocuc.aduana_reportes_api.exception.ReporteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReporteService {

    private static final Logger log = LoggerFactory.getLogger(ReporteService.class);

    private final AduanaClient aduanaClient;

    public ReporteService(AduanaClient aduanaClient) {
        this.aduanaClient = aduanaClient;
    }

    public List<ReporteResponseDTO> obtenerTodos() {
        log.info("Obteniendo lista de todos los reportes desde Aduana-Api");
        ApiResponse<List<ReporteResponseDTO>> response = aduanaClient.obtenerReportes();
        log.info("Reportes obtenidos: {}", response.getData().size());
        return response.getData();
    }

    public ReporteResponseDTO buscarPorId(Long id) {
        log.info("Buscando reporte con id: {} en Aduana-Api", id);
        ApiResponse<ReporteResponseDTO> response = aduanaClient.obtenerReportePorId(id);
        if (response == null || response.getData() == null) {
            log.error("Reporte con id {} no encontrado", id);
            throw new ReporteNotFoundException("Reporte con id " + id + " no encontrado");
        }
        return response.getData();
    }

    public List<ReporteResponseDTO> obtenerPorUsuario(Long idUsuario) {
        log.info("Obteniendo reportes del usuario con id: {} desde Aduana-Api", idUsuario);
        ApiResponse<List<ReporteResponseDTO>> response = aduanaClient.obtenerReportesPorUsuario(idUsuario);
        log.info("Reportes del usuario {} obtenidos: {}", idUsuario, response.getData().size());
        return response.getData();
    }

    public List<ReporteResponseDTO> obtenerPorTipo(String tipo) {
        log.info("Obteniendo reportes de tipo: {} desde Aduana-Api", tipo);
        ApiResponse<List<ReporteResponseDTO>> response = aduanaClient.obtenerReportesPorTipo(tipo);
        log.info("Reportes de tipo {} obtenidos: {}", tipo, response.getData().size());
        return response.getData();
    }

    public ReporteResponseDTO registrarReporte(ReporteRequestDTO dto) {
        log.info("Registrando reporte tipo: {} para usuario id: {} en Aduana-Api",
                dto.getTipo(), dto.getIdUsuario());
        ApiResponse<ReporteResponseDTO> response = aduanaClient.registrarReporte(dto);
        log.info("Reporte registrado con id: {}", response.getData().getIdReporte());
        return response.getData();
    }

    public ApiResponse<List<PasajeroResponse>> generarReportePasajeros() {
        log.info("Generando reporte consolidado de pasajeros desde Aduana-Api");
        ApiResponse<List<PasajeroResponse>> response = aduanaClient.obtenerPasajeros();
        log.info("Reporte generado con {} pasajeros", response.getData().size());
        return response;
    }

    public void eliminarReporte(Long id) {
        log.info("Eliminando reporte con id: {} en Aduana-Api", id);
        aduanaClient.eliminarReporte(id);
        log.info("Reporte con id {} eliminado", id);
    }
}