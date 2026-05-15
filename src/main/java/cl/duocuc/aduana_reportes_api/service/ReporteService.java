package cl.duocuc.aduana_reportes_api.service;

import cl.duocuc.aduana_reportes_api.model.Reporte;
import cl.duocuc.aduana_reportes_api.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {
    @Autowired private ReporteRepository repository;

    public Reporte generarReporte(Reporte reporte) {
        // Lógica de negocio: Verificar permisos de supervisor para exportar
        return repository.save(reporte);
    }
}