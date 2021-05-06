package proyectoFCT.gestorLicencias.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Club {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idClub;

    @Column(name = "licenciaClub", nullable = false)
    private String licenciaClub;

    @Column(name = "fechaCreacion", nullable = false)
    private String fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idDeportista",
            foreignKey = @ForeignKey(name = "Deportista_Id_FK"))
    private Deportista deportista;

    @ManyToOne
    @JoinColumn(name = "idEntrenador",
            foreignKey = @ForeignKey(name = "Entrenador_Id_FK"))
    private Entrenador entrenador;
}
