package cl.duocuc.aduana_reportes_api.service;

import cl.duocuc.aduana_reportes_api.model.Reporte;
import cl.duocuc.aduana_reportes_api.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReporteService {

    private final  ReporteRepository repository;

    @Autowired
    public ReporteService(ReporteRepository repository) {
        this.repository = repository;
    }


    public Reporte generarReporte(Reporte reporte) {
        // Lógica de negocio: Verificar permisos de supervisor para exportar
        return repository.save(reporte);
    }

    public List<Reporte> obtenerTodos() {
        return repository.findAll();
    }
}