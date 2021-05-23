package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadFindAllDto;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;

import java.io.IOException;
import java.util.List;

public interface PersonaEspecialidadService {

    PersonaEspecialidadDTO crearPersona(PersonaEspecialidadDTO personaEspecialidadDTO) throws IOException;

    List<PersonaEspecialidadFindAllDto> findAll();
}
