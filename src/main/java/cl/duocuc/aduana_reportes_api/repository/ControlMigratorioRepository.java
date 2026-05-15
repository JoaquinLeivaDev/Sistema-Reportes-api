package cl.duocuc.aduana_reportes_api.repository;

import cl.duocuc.aduana_reportes_api.model.ControlMigratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlMigratorioRepository extends JpaRepository<ControlMigratorio, Long> {
}