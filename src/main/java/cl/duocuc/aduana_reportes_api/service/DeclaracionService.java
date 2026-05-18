package cl.duocuc.aduana_reportes_api.service;

import cl.duocuc.aduana_reportes_api.client.AduanaClient;
import cl.duocuc.aduana_reportes_api.dto.*;
import cl.duocuc.aduana_reportes_api.exception.DeclaracionNotFoundException;
import cl.duocuc.aduana_reportes_api.model.Declaracion;
import cl.duocuc.aduana_reportes_api.repository.DeclaracionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeclaracionService {

    private static final Logger log = LoggerFactory.getLogger(DeclaracionService.class);

    private final DeclaracionRepository declaracionRepository;
    private final AduanaClient aduanaClient;

    public DeclaracionService(DeclaracionRepository declaracionRepository,
                              AduanaClient aduanaClient) {
        this.declaracionRepository = declaracionRepository;
        this.aduanaClient = aduanaClient;
    }

    public List<DeclaracionResponseDTO> obtenerTodas() {
        log.info("Obteniendo lista de todas las declaraciones");
        return declaracionRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public DeclaracionResponseDTO buscarPorId(Long id) {
        log.info("Buscando declaración con id: {}", id);
        Declaracion declaracion = declaracionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Declaración con id {} no encontrada", id);
                    return new DeclaracionNotFoundException(
                            "Declaración con id " + id + " no encontrada");
                });
        return toResponseDTO(declaracion);
    }

    public List<DeclaracionResponseDTO> buscarPorPasajero(Long idPasajero) {
        log.info("Buscando declaraciones del pasajero con id: {}", idPasajero);
        return declaracionRepository.findByIdPasajero(idPasajero)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public DeclaracionResponseDTO registrarDeclaracion(DeclaracionRequestDTO dto) {
        log.info("Registrando declaración tipo: {} para pasajero id: {}",
                dto.getTipo(), dto.getIdPasajero());

        // Verificar que el pasajero existe en Aduana-Api via Feign
        log.info("Verificando pasajero con id: {} en Aduana-Api", dto.getIdPasajero());
        ApiResponse<PasajeroResponse> response = aduanaClient.obtenerPasajeroPorId(dto.getIdPasajero());
        if (response == null || response.getData() == null) {
            throw new IllegalArgumentException("Pasajero con id " + dto.getIdPasajero() + " no encontrado");
        }

        Declaracion declaracion = new Declaracion();
        declaracion.setTipo(dto.getTipo());
        declaracion.setDetalles(dto.getDetalles());
        declaracion.setIdPasajero(dto.getIdPasajero());

        Declaracion guardada = declaracionRepository.save(declaracion);
        log.info("Declaración registrada con id: {}", guardada.getId());
        return toResponseDTO(guardada);
    }

    // Regla de negocio clave: firmar declaración
    public DeclaracionResponseDTO firmarDeclaracion(Long id) {
        log.info("Firmando declaración con id: {}", id);
        Declaracion declaracion = declaracionRepository.findById(id)
                .orElseThrow(() -> new DeclaracionNotFoundException(
                        "Declaración con id " + id + " no encontrada"));

        declaracion.firmar();
        Declaracion firmada = declaracionRepository.save(declaracion);
        log.info("Declaración con id {} firmada exitosamente", id);
        return toResponseDTO(firmada);
    }

    public void eliminarDeclaracion(Long id) {
        log.info("Eliminando declaración con id: {}", id);
        if (!declaracionRepository.existsById(id)) {
            log.error("No se puede eliminar: declaración con id {} no existe", id);
            throw new DeclaracionNotFoundException("Declaración con id " + id + " no encontrada");
        }
        declaracionRepository.deleteById(id);
        log.info("Declaración con id {} eliminada exitosamente", id);
    }

    private DeclaracionResponseDTO toResponseDTO(Declaracion d) {
        // Obtener nombre del pasajero desde Aduana-Api
        String nombrePasajero = "";
        try {
            ApiResponse<PasajeroResponse> response = aduanaClient.obtenerPasajeroPorId(d.getIdPasajero());
            if (response != null && response.getData() != null) {
                nombrePasajero = response.getData().getNombre();
            }
        } catch (Exception e) {
            log.warn("No se pudo obtener nombre del pasajero con id: {}", d.getIdPasajero());
        }

        return new DeclaracionResponseDTO(
                d.getId(),
                d.getTipo(),
                d.getDetalles(),
                d.getIdPasajero(),
                nombrePasajero
        );
    }
}