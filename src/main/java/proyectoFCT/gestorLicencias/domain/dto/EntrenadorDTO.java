package proyectoFCT.gestorLicencias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrenadorDTO implements Serializable {

    private String numLicenciaEntrenador;

    private String DNI;

    private String nombre;

    private Integer telefono;

    private String email;
}
