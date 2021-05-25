package proyectoFCT.gestorLicencias.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CrearLicenciaDTO implements Serializable {

    private String nombreEspecialidad;

    private Long codPersona;

    private String nivel;

    private String fechaActivacion;

    private Boolean esDeportista;

    private Boolean esEntrenador;

    private Boolean esJuez;
}
