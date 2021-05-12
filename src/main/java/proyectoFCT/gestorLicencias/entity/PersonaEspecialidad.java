package proyectoFCT.gestorLicencias.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PersonaEspecialidad {

    @EmbeddedId
    PersonaEspecialidadId id;

    @ManyToOne
    @MapsId("idPersona")
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @MapsId("idEspecialidad")
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @Column(name = "fechaActivacion")
    private String fechaActivacion;

    @Column(name = "nivel")
    private String nivel;

    PersonaEspecialidad(Persona persona, Long idEspecialidad,String fecha, String nivel){
        this.persona = persona;
        this.especialidad.setIdEspecialidad(idEspecialidad);
        this.fechaActivacion = fecha;
        this.nivel = nivel;
    }

    public PersonaEspecialidad() {

    }
}
