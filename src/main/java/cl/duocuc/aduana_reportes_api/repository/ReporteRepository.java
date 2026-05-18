// ReporteRepository.java
package cl.duocuc.aduana_reportes_api.repository;

import cl.duocuc.aduana_reportes_api.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByIdUsuario(Long idUsuario);
    List<Reporte> findByTipo(String tipo);
}