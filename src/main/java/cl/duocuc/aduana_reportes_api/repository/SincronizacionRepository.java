package cl.duocuc.aduana_reportes_api.repository;

import cl.duocuc.aduana_reportes_api.model.Sincronizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SincronizacionRepository extends JpaRepository<Sincronizacion, Long> {
    // Si necesitas obtener la última sincronización, puedes agregar:
    // Sincronizacion findTopByOrderByUltimoSyncDesc();
}