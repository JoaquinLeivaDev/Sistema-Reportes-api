package cl.duocuc.aduana_reportes_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sincronizacion")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Sincronizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado_enlace", nullable = false)
    private boolean estadoEnlace;

    @Column(name = "ultimo_sync", nullable = false)
    private LocalDateTime ultimoSync;

    @ManyToOne
    @JoinColumn(name = "id_usuario") // Usuario que monitorea el enlace
    private Usuario usuario;
}