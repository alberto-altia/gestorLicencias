package proyectoFCT.gestorLicencias.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PersonaEspecialidadId implements Serializable {

    @Column(name = "persona_id")
    private Long persona;

    @Column(name = "especialidad_id")
    private Long especialidad;
}
