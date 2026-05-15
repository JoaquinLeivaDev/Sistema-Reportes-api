package cl.duocuc.aduana_reportes_api.service;

import cl.duocuc.aduana_reportes_api.model.Declaracion;
import cl.duocuc.aduana_reportes_api.repository.DeclaracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeclaracionService {
    @Autowired private DeclaracionRepository repository;

    public Declaracion guardarDeclaracion(Declaracion declaracion) {
        // Lógica para validar si el producto está en la lista de prohibidos
        return repository.save(declaracion);
    }
}