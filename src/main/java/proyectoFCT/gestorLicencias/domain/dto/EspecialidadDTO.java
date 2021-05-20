package proyectoFCT.gestorLicencias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadDTO implements Serializable {

    private Long idEspecialidad;

    private String nombre;

    private Set<PersonaEspecialidad> personaEspecialidad;
}
