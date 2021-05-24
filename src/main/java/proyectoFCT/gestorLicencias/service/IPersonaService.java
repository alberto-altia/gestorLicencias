package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.LoginDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;

public interface IPersonaService {

	PersonaDTO createOrUpdate(PersonaDTO input);

	PersonaDTO login (LoginDTO loginDTO);
}
