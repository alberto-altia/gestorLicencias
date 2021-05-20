package proyectoFCT.gestorLicencias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEspecialidadDTO implements Serializable {

    private EspecialidadDTO especialidad;

    private PersonaDTO persona;

    private String fechaActivacion;

    private String nivel;

    private Boolean esDeportista;

    private Boolean esEntrenador;

    private Boolean  esJuez;
}
