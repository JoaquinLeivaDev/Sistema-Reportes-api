package cl.duocuc.aduana_reportes_api.controller;

import cl.duocuc.aduana_reportes_api.dto.ReporteDTO;
import cl.duocuc.aduana_reportes_api.model.Reporte;
import cl.duocuc.aduana_reportes_api.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService service;

    // GET: Listar todos los reportes generados
    @GetMapping
    public ResponseEntity<List<Reporte>> listarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    // POST: Generar un nuevo reporte
    @PostMapping
    public ResponseEntity<Reporte> generar(@Valid @RequestBody ReporteDTO dto) {
        // Mapeo manual de DTO a Entidad (esto asegura la separación de capas)
        Reporte reporte = new Reporte();
        reporte.setTipo(dto.getTipo());
        reporte.setDatos(dto.getDatos());
        reporte.setFecha(java.time.LocalDate.now());

        return ResponseEntity.ok(service.generarReporte(reporte));
    }
}