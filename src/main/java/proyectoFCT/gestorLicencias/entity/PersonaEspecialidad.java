package proyectoFCT.gestorLicencias.entity;

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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codEspecialidad", referencedColumnName = "idEspecialidad")
    private Especialidad especialidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codPersona", referencedColumnName = "idPersona")
    private Persona persona;

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