package proyectoFCT.gestorLicencias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5207022665210393259L;

	private Long idPersona;

    private String DNI;

    private String nombreApellidos;

    private String fechaNacimiento;

    private Double telefono;

    private String email;

    private String numLicenciaDeportista;

    private String numLicenciaEntrenador;

    private String numLicenciaJuez;

    private Long codClub;

    private String usuario;

    private String password;
}
