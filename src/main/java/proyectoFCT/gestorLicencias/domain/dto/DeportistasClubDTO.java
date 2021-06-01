package proyectoFCT.gestorLicencias.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DeportistasClubDTO implements Serializable {

    private Long idPersona;

    private String nombreApellidos;

    private String dni;

    private String numLicenciaDeportista;


}
