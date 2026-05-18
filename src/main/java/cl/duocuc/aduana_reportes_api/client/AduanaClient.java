package cl.duocuc.aduana_reportes_api.client;

import cl.duocuc.aduana_reportes_api.dto.ApiResponse;
import cl.duocuc.aduana_reportes_api.dto.PasajeroResponse;
import cl.duocuc.aduana_reportes_api.dto.VehiculoResponse;
import cl.duocuc.aduana_reportes_api.dto.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "aduana-api", url = "${aduana.api.url}")
public interface AduanaClient {

    @GetMapping("/api/v1/pasajeros")
    ApiResponse<List<PasajeroResponse>> obtenerPasajeros();

    @GetMapping("/api/v1/pasajeros/{id}")
    ApiResponse<PasajeroResponse> obtenerPasajeroPorId(@PathVariable Long id);

    @GetMapping("/api/v1/vehiculos")
    ApiResponse<List<VehiculoResponse>> obtenerVehiculos();

    @GetMapping("/api/v1/usuarios/{id}")
    ApiResponse<UsuarioResponse> obtenerUsuarioPorId(@PathVariable Long id);
}