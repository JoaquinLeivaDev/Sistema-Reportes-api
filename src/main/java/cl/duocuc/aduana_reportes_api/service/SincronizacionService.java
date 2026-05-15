package cl.duocuc.aduana_reportes_api.service;

import cl.duocuc.aduana_reportes_api.model.Sincronizacion;
import cl.duocuc.aduana_reportes_api.repository.SincronizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SincronizacionService {
    @Autowired private SincronizacionRepository repository;

    public void actualizarSincronizacion(boolean estado) {
        Sincronizacion sync = new Sincronizacion();
        sync.setEstadoEnlace(estado);
        sync.setUltimoSync(LocalDateTime.now());
        repository.save(sync);
    }
}