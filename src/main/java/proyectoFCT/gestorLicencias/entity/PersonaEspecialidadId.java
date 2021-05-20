package proyectoFCT.gestorLicencias.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class PersonaEspecialidadId implements Serializable {

    @Column(name = "persona_id")
    private Long personaID;

    @Column(name = "especialidad_id")
    private Long especialidadID;
}
