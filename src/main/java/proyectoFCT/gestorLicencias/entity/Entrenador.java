package proyectoFCT.gestorLicencias.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
@Getter @Setter
public class Entrenador extends Persona {

    @Column(name = "numLicenciaEntrenador")
    private String numLicenciaEntrenador;

    public Entrenador(String numLicenciaEntrenador){
        this.numLicenciaEntrenador = numLicenciaEntrenador;
    };
    public Entrenador() {

    }
}
