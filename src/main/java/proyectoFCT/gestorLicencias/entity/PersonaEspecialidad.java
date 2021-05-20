package proyectoFCT.gestorLicencias.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PersonaEspecialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    PersonaEspecialidadId id;

    @ManyToOne
    @MapsId("personaID")
    @JoinColumn(name = "persona_id")
    private Persona persona;
//as
    @ManyToOne
    @MapsId("especialidadID")
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @Column(name = "fechaActivacion")
    private String fechaActivacion;

    @Column(name = "nivel")
    private String nivel;

    private Boolean esDeportista;

    private Boolean esEntrenador;

    private Boolean esJuez;
}
/*
INSERT INTO PERSONA_ESPECIALIDAD  VALUES(1,2,true,false,false,'17/05/2021','A Nacional');
 */