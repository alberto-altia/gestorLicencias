package proyectoFCT.gestorLicencias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEspecialidadCrearDTO implements Serializable {

    private String nombreEspecialidad;

    private PersonaDTO persona;

    private String fechaActivacion;

    private String nivel;
}
