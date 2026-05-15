package cl.duocuc.aduana_reportes_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "control_migratorio")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ControlMigratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_control")
    private Long id;

    @Column(nullable = false, length = 20)
    private String estado; // Pendiente, Aprobado, Rechazado

    @Lob
    @Column(columnDefinition = "CLOB")
    private String observaciones;

    @OneToOne
    @JoinColumn(name = "id_pasajero", nullable = false)
    private Pasajero pasajero;
}