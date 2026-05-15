package cl.duocuc.aduana_reportes_api.repository;

import cl.duocuc.aduana_reportes_api.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    // Si necesitas buscar reportes por tipo, podrías agregar:
    // List<Reporte> findByTipo(String tipo);
}