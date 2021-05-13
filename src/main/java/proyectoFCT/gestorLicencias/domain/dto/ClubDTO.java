package proyectoFCT.gestorLicencias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubDTO implements Serializable {

    private String numLicenciaClub;

    private String nombreClub;

    private String fechaCreacion;

    private String numLicenciaEntrenador;
}