package proyectoFCT.gestorLicencias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proyectoFCT.gestorLicencias.entity.Club;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeportistaDTO implements Serializable {

    private String numLicenciaDeportista;

    private Club clubDTO;

    private String DNI;

    private String nombre;

    private Integer telefono;

    private String email;
}
