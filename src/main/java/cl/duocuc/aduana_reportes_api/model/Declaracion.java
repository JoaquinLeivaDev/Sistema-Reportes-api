package cl.duocuc.aduana_reportes_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "declaracion")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Declaracion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "declaracion_seq")
    @SequenceGenerator(name = "declaracion_seq", sequenceName = "DECLARACION_SEQ", allocationSize = 1)
    @Column(name = "id_declaracion")
    private Long id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Lob
    @Column(name = "detalles", columnDefinition = "CLOB")
    private String detalles;

    @Column(name = "id_pasajero", nullable = false)
    private Long idPasajero;

    // Método del diagrama de clases
    public void firmar() {
        if (this.detalles == null || this.detalles.isBlank()) {
            throw new IllegalArgumentException("No se puede firmar una declaración sin detalles");
        }
        this.detalles = "[FIRMADO] " + this.detalles;
    }
}