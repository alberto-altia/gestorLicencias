package proyectoFCT.gestorLicencias.entity;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
public class Entrenador extends Persona {

    @Column(name = "numLicenciaEntrenador")
    private String numLicenciaEntrenador;



    public Entrenador(String numLicenciaEntrenador, String dni, String nombre, String fecha, Integer telefono, String email) {
        super(dni, nombre, fecha, telefono, email);
        this.numLicenciaEntrenador = numLicenciaEntrenador;
    }

    public Entrenador() {

    }

    @ManyToOne(optional = false)
    private Club clubs;

    public Club getClubs() {
        return clubs;
    }

    public void setClubs(Club clubs) {
        this.clubs = clubs;
    }
}
