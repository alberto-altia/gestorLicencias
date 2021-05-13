package proyectoFCT.gestorLicencias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyectoFCT.gestorLicencias.entity.Club;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO implements Serializable {

    private String dni;

    private String nombreApellidos;

    private String fechaNacimiento;

    private String telefono;

    private String email;

    private String numLicenciaDeportista;

    private String numLicenciaEntrenador;

    private String numLicenciaJuez;

    private Club club;
}
