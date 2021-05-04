package proyectoFCT.gestorLicencias.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PersonaEspecialidad {

    @EmbeddedId
    PersonaEspecialidadId id;

    @MapsId("persona_id")
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @MapsId("especialidad_id")
    @ManyToOne
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
}
