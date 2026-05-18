package cl.duocuc.aduana_reportes_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reporte")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reporte_seq")
    @SequenceGenerator(name = "reporte_seq", sequenceName = "REPORTE_SEQ", allocationSize = 1)
    @Column(name = "id_reporte")
    private Long id;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false)
    private LocalDate fecha;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String datos;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;
}