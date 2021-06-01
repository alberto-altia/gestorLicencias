package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.LoginDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;

import java.util.List;

public interface PersonaService {

    PersonaDTO create(PersonaDTO input);

    PersonaDTO createEntrenador(PersonaDTO input);

    List<PersonaDTO> allPersonas();

    PersonaDTO login(LoginDTO loginDTO);

    PersonaDTO Update(PersonaDTO input);
}
