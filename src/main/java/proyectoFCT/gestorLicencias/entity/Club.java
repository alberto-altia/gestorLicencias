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

    @Column(name = "licenciaClub",nullable = false)
    private String licenciaClub;

    @Column(name = "fechaCreacion",nullable = false)
    private String fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idPersona",
            foreignKey = @ForeignKey(name="PERSONA_ID_FK"))
    private Deportista deportista;
}
