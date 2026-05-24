package cl.duocuc.aduana_reportes_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String mensaje;
    private boolean error;
    private T data;
}