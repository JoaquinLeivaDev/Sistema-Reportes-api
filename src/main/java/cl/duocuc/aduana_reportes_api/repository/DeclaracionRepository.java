// DeclaracionRepository.java
package cl.duocuc.aduana_reportes_api.repository;

import cl.duocuc.aduana_reportes_api.model.Declaracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeclaracionRepository extends JpaRepository<Declaracion, Long> {
    List<Declaracion> findByIdPasajero(Long idPasajero);
}