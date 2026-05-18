// DeclaracionController.java
package cl.duocuc.aduana_reportes_api.controller;

import cl.duocuc.aduana_reportes_api.dto.*;
import cl.duocuc.aduana_reportes_api.service.DeclaracionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/declaraciones")
public class DeclaracionController {

    private static final Logger log = LoggerFactory.getLogger(DeclaracionController.class);

    private final DeclaracionService service;

    public DeclaracionController(DeclaracionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DeclaracionResponseDTO>>> listarTodas() {
        log.info("GET /api/v1/declaraciones - Listando declaraciones");
        return ResponseEntity.ok(ApiResponse.ok(service.obtenerTodas(), "Declaraciones obtenidas"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeclaracionResponseDTO>> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/v1/declaraciones/{}", id);
        return ResponseEntity.ok(ApiResponse.ok(service.buscarPorId(id), "Declaración encontrada"));
    }

    @GetMapping("/pasajero/{idPasajero}")
    public ResponseEntity<ApiResponse<List<DeclaracionResponseDTO>>> buscarPorPasajero(
            @PathVariable Long idPasajero) {
        log.info("GET /api/v1/declaraciones/pasajero/{}", idPasajero);
        return ResponseEntity.ok(ApiResponse.ok(
                service.buscarPorPasajero(idPasajero), "Declaraciones del pasajero obtenidas"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DeclaracionResponseDTO>> registrar(
            @RequestBody @Valid DeclaracionRequestDTO dto) {
        log.info("POST /api/v1/declaraciones - Registrando declaración tipo: {}", dto.getTipo());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok(service.registrarDeclaracion(dto), "Declaración registrada"));
    }

    // Endpoint clave del dominio: firmar declaración
    @PatchMapping("/{id}/firmar")
    public ResponseEntity<ApiResponse<DeclaracionResponseDTO>> firmar(@PathVariable Long id) {
        log.info("PATCH /api/v1/declaraciones/{}/firmar - Firmando declaración", id);
        return ResponseEntity.ok(ApiResponse.ok(
                service.firmarDeclaracion(id), "Declaración firmada exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/v1/declaraciones/{}", id);
        service.eliminarDeclaracion(id);
        return ResponseEntity.ok(ApiResponse.ok(null, "Declaración eliminada"));
    }
}