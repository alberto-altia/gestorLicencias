package proyectoFCT.gestorLicencias.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(PesonaEspecialidadId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEspecialidad {

    @Id
    @ManyToOne
    @JoinColumn(
            name="idPersona",
            insertable = false, updatable = false
    )
    private Persona persona;

    @Id
    @ManyToOne
    @JoinColumn(
            name="idEspecialidad",
            insertable = false, updatable = false
    )
    private Especialidad especialidad;

    @Column(name = "fechaActivacion")
    private String fechaActivacion;

    @Column(name = "nivel")
    private String nivel;

    @Column (name = "esDeportista")
    private Boolean esDeportista;

    @Column (name = "esEntrenador")
    private Boolean esEntrenador;

    @Column (name= "esJuez")
    private Boolean esJuez;

    public PersonaEspecialidad(Persona persona){
        this.persona = persona;
    }

    public PersonaEspecialidad(Persona persona, Especialidad especialidad, String nivel, Boolean esDeportista, Boolean esEntrenador, Boolean esJuez) {
        this.persona = persona;
        this.especialidad = especialidad;
        this.nivel = nivel;
        this.esDeportista = esDeportista;
        this.esEntrenador = esEntrenador;
        this.esJuez = esJuez;
    }
}
