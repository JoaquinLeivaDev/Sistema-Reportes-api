package cl.duocuc.aduana_reportes_api.service;

import cl.duocuc.aduana_reportes_api.client.AduanaClient;
import cl.duocuc.aduana_reportes_api.dto.*;
import cl.duocuc.aduana_reportes_api.exception.ReporteNotFoundException;
import cl.duocuc.aduana_reportes_api.model.Reporte;
import cl.duocuc.aduana_reportes_api.repository.ReporteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReporteService {

    private static final Logger log = LoggerFactory.getLogger(ReporteService.class);

    private final ReporteRepository reporteRepository;
    private final AduanaClient aduanaClient;

    public ReporteService(ReporteRepository reporteRepository,
                          AduanaClient aduanaClient) {
        this.reporteRepository = reporteRepository;
        this.aduanaClient = aduanaClient;
    }

    public List<ReporteResponseDTO> obtenerTodos() {
        log.info("Obteniendo lista de todos los reportes");
        return reporteRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ReporteResponseDTO buscarPorId(Long id) {
        log.info("Buscando reporte con id: {}", id);
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Reporte con id {} no encontrado", id);
                    return new ReporteNotFoundException("Reporte con id " + id + " no encontrado");
                });
        return toResponseDTO(reporte);
    }

    public ReporteResponseDTO registrarReporte(ReporteRequestDTO dto) {
        log.info("Registrando reporte tipo: {} para usuario id: {}",
                dto.getTipo(), dto.getIdUsuario());

        // Verificar que el usuario existe en Aduana-Api via Feign
        log.info("Verificando usuario con id: {} en Aduana-Api", dto.getIdUsuario());
        ApiResponse<UsuarioResponse> response = aduanaClient.obtenerUsuarioPorId(dto.getIdUsuario());
        if (response == null || response.getData() == null) {
            throw new IllegalArgumentException("Usuario con id " + dto.getIdUsuario() + " no encontrado");
        }

        Reporte reporte = new Reporte();
        reporte.setTipo(dto.getTipo());
        reporte.setFecha(dto.getFecha());
        reporte.setDatos(dto.getDatos());
        reporte.setIdUsuario(dto.getIdUsuario());

        Reporte guardado = reporteRepository.save(reporte);
        log.info("Reporte registrado con id: {}", guardado.getId());
        return toResponseDTO(guardado);
    }

    public List<ReporteResponseDTO> obtenerPorUsuario(Long idUsuario) {
        log.info("Obteniendo reportes del usuario con id: {}", idUsuario);
        return reporteRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Regla de negocio: generar reporte consolidado con datos de pasajeros
    public ApiResponse<List<PasajeroResponse>> generarReportePasajeros() {
        log.info("Generando reporte consolidado de pasajeros desde Aduana-Api");
        ApiResponse<List<PasajeroResponse>> response = aduanaClient.obtenerPasajeros();
        log.info("Reporte generado con {} pasajeros", response.getData().size());
        return response;
    }

    public void eliminarReporte(Long id) {
        log.info("Eliminando reporte con id: {}", id);
        if (!reporteRepository.existsById(id)) {
            log.error("No se puede eliminar: reporte con id {} no existe", id);
            throw new ReporteNotFoundException("Reporte con id " + id + " no encontrado");
        }
        reporteRepository.deleteById(id);
        log.info("Reporte con id {} eliminado exitosamente", id);
    }

    private ReporteResponseDTO toResponseDTO(Reporte r) {
        return new ReporteResponseDTO(
                r.getId(),
                r.getTipo(),
                r.getFecha(),
                r.getDatos(),
                r.getIdUsuario()
        );
    }
}