package proyectoFCT.gestorLicencias.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
@Getter @Setter
public class Deportista extends Persona {

    @Column(name = "numLicenciaDeportista")
    private String numLicenciaDeportista;

    @ManyToOne
    @JoinColumn(name = "idClub",
            foreignKey = @ForeignKey(name = "Club_Id_FK"))
    private Club club;
}
