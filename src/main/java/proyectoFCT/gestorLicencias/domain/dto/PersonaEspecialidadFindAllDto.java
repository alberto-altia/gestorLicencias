package proyectoFCT.gestorLicencias.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonaEspecialidadFindAllDto implements Serializable {

	private long id;

	private long codPersona;

	private long codEspecialidad;

	private boolean esEntrenador;

	private boolean esJuez;

	private String fechaActivacion;

	private String nivel;

	private boolean esDeportista;
}
