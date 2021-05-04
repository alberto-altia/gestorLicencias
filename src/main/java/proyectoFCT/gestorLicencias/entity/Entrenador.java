package proyectoFCT.gestorLicencias.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
public class Entrenador extends Persona {

    @Column(name = "numLicenciaEntrenador")
    private String numLicenciaEntrenador;

    public Entrenador(String numLicenciaEntrenador, String dni, String nombre, String fecha, Integer telefono, String email) {
        super(dni, nombre, fecha, telefono, email);
        this.numLicenciaEntrenador = numLicenciaEntrenador;
    }
}
