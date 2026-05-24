package cl.duocuc.aduana_reportes_api.controller;

import cl.duocuc.aduana_reportes_api.dto.*;
import cl.duocuc.aduana_reportes_api.service.ReporteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    private final ReporteService service;

    @GetMapping
    public ApiResponse<List<ReporteResponseDTO>> listarTodos() { return service.obtenerTodos(); }

    @GetMapping("/{id}")
    public ApiResponse<ReporteResponseDTO> buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @GetMapping("/usuario/{idUsuario}")
    public ApiResponse<List<ReporteResponseDTO>> buscarPorUsuario(@PathVariable Long idUsuario) { return service.obtenerPorUsuario(idUsuario); }

    @GetMapping("/consolidado/pasajeros")
    public ApiResponse<List<PasajeroResponse>> reportePasajeros() { return service.generarReportePasajeros(); }

    @PostMapping
    public ApiResponse<ReporteResponseDTO> registrar(@RequestBody @Valid ReporteRequestDTO dto) { return service.registrarReporte(dto); }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Long id) { return service.eliminarReporte(id); }
}