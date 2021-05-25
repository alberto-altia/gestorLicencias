package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.LoginDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;

public interface IPersonaService {

	PersonaDTO create(PersonaDTO input, Boolean esEntrenador, Boolean esDeportista, Boolean esJuez);

	PersonaDTO login (LoginDTO loginDTO);

	PersonaDTO Update(PersonaDTO input);
}
