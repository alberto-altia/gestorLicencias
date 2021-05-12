package proyectoFCT.gestorLicencias.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
@Getter
@Setter
public class Juez extends Persona {

    @Column(name = "numLicenciaJuez")
    private String numLicenciaJuez;

}

