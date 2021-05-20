package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.convertidor.ConvertidorPersona;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadDTO;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;
import proyectoFCT.gestorLicencias.repository.PersonaEspecialidadRepository;
import proyectoFCT.gestorLicencias.service.PersonaEspecialidadService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonaEspecialidadServiceImpl implements PersonaEspecialidadService {

    @Autowired
    ConvertidorPersona convertidorPersona;

    @Autowired
    PersonaEspecialidadRepository personaEspecialidadRepository;

    @Override
    @Transactional
    public PersonaEspecialidadDTO crearPersona(PersonaEspecialidadDTO personaEspecialidadDTO) {
        personaEspecialidadRepository.save(convertidorPersona.convertirPersonaEspecialidadDTOtoEntity(personaEspecialidadDTO));
        return personaEspecialidadDTO;
    }

    @Override
    public List<PersonaEspecialidad> findAll() {
        return (List<PersonaEspecialidad>) personaEspecialidadRepository.findAll();
    }
}
