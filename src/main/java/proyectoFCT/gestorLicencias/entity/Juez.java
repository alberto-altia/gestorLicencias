package proyectoFCT.gestorLicencias.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
public class Juez extends Persona {

    @Column(name = "numLicenciaJuez")
    private String numLicenciaJuez;

    public Juez(String numLicenciaJuez, String dni, String nombre, String fecha, Integer telefono, String email) {
        super(dni, nombre, fecha, telefono, email);
        this.numLicenciaJuez = numLicenciaJuez;
    }
}

