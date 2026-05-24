package cl.duocuc.aduana_reportes_api.service;

import cl.duocuc.aduana_reportes_api.client.AduanaClient;
import cl.duocuc.aduana_reportes_api.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final AduanaClient aduanaClient;

    public ApiResponse<List<ReporteResponseDTO>> obtenerTodos() {
        return aduanaClient.obtenerReportes();
    }

    public ApiResponse<ReporteResponseDTO> buscarPorId(Long id) {
        return aduanaClient.obtenerReportePorId(id);
    }

    public ApiResponse<List<ReporteResponseDTO>> obtenerPorUsuario(Long idUsuario) {
        return aduanaClient.obtenerReportesPorUsuario(idUsuario);
    }

    public ApiResponse<List<PasajeroResponse>> generarReportePasajeros() {
        return aduanaClient.obtenerPasajeros();
    }

    public ApiResponse<ReporteResponseDTO> registrarReporte(ReporteRequestDTO dto) {
        return aduanaClient.registrarReporte(dto);
    }

    public ApiResponse<Void> eliminarReporte(Long id) {
        return aduanaClient.eliminarReporte(id);
    }
}