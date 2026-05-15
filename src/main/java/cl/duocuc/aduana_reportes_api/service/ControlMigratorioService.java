package cl.duocuc.aduana_reportes_api.service;

import cl.duocuc.aduana_reportes_api.model.ControlMigratorio;
import cl.duocuc.aduana_reportes_api.repository.ControlMigratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ControlMigratorioService {

    @Autowired
    private ControlMigratorioRepository repository;

    // Obtener todas las revisiones
    public List<ControlMigratorio> obtenerTodos() {
        return repository.findAll();
    }

    // Registrar una nueva revisión PDI
    public ControlMigratorio registrarRevision(ControlMigratorio control) {
        // Lógica de negocio: Verificar reglas de arraigo o alertas PDI
        return repository.save(control);
    }

    // Buscar una revisión específica
    public ControlMigratorio buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Revisión PDI no encontrada"));
    }
}