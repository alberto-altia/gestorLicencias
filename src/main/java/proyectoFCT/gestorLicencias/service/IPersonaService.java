package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;

public interface IPersonaService {
	PersonaDTO createOrUpdate(PersonaDTO input);
}
