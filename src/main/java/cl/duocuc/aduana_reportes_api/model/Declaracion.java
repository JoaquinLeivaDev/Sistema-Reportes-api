package cl.duocuc.aduana_reportes_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "declaracion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Declaracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_declaracion")
    private Long id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo; // "Alimentos", "Mascotas", etc.
    @Lob
    @Column(name = "detalles", columnDefinition = "CLOB")
    private String detalles;

    /*
     * Relación muchos a uno con Pasajero.
     * Muchas declaraciones pueden pertenecer a un solo pasajero.
     */
    @ManyToOne
    @JoinColumn(name = "id_pasajero", nullable = false)
    private Pasajero pasajero;
}