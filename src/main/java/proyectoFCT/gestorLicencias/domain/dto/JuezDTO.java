package proyectoFCT.gestorLicencias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JuezDTO implements Serializable {

    private String numLicenciaJuez;

    private String DNI;

    private String nombre;

    private Integer telefono;

    private String email;
}
