package proyectoFCT.gestorLicencias.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PersonaEspecialidad {

    @EmbeddedId
    PersonaEspecialidadId id;

    @ManyToOne
    @MapsId("persona_id")
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @MapsId("especialidad_id")
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @Column(name = "fechaActivacion")
    private String fechaActivacion;

    @Column(name = "nivel")
    private String nivel;
}
