package proyectoFCT.gestorLicencias.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LicenciasActivasDTO implements Serializable {

    private Long idLicencia;

    private String nombreEspecialidad;

    private String nivel;

    private String fechaActivacion;

    private String tipoLicencia;
}
