package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.convertidor.ConvertidorPersona;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadFindAllDto;
import proyectoFCT.gestorLicencias.entity.Especialidad;
import proyectoFCT.gestorLicencias.entity.Persona;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;
import proyectoFCT.gestorLicencias.repository.IEspecialidadRepository;
import proyectoFCT.gestorLicencias.repository.PersonaEspecialidadRepository;
import proyectoFCT.gestorLicencias.repository.PersonaRepository;
import proyectoFCT.gestorLicencias.service.IPersonaService;
import proyectoFCT.gestorLicencias.service.PersonaEspecialidadService;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaEspecialidadServiceImpl implements PersonaEspecialidadService {

    @Autowired
    IEspecialidadRepository especialidadRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ConvertidorPersona convertidorPersona;

    @Autowired
    IPersonaService personaService;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PersonaEspecialidadRepository personaEspecialidadRepository;

    @Override
    @Transactional
    public PersonaEspecialidadDTO crearPersona(PersonaEspecialidadDTO personaEspecialidadDTO) {
        //Especialidad non existe
    	if (!especialidadRepository.existsById(personaEspecialidadDTO.getCodEspecialidad()))
            throw new BadRequestException("Codigo especialidad no valido");
    	//Usuario collido
        if(personaRepository.existsPersonaByUsuario(personaEspecialidadDTO.getPersona().getUsuario()))
            throw new BadRequestException("Usuario existente");

        PersonaEspecialidad pEsp = new PersonaEspecialidad();
        BeanUtils.copyProperties(personaEspecialidadDTO, pEsp);

        pEsp.setPersona(entityManager.getReference(Persona.class, personaService.createOrUpdate(personaEspecialidadDTO.getPersona()).getIdPersona()));
        pEsp.setEspecialidad(entityManager.getReference(Especialidad.class, personaEspecialidadDTO.getCodEspecialidad()));
        personaEspecialidadRepository.save(pEsp);
        return personaEspecialidadDTO;
    }

    @Override
    public List<PersonaEspecialidadFindAllDto> findAll() {
        return personaEspecialidadRepository.findAll()
        									.stream()
        									.map(this::toDto)
        									.collect(Collectors.toList());
    }
    
    public PersonaEspecialidadFindAllDto toDto(PersonaEspecialidad entity) {
    	PersonaEspecialidadFindAllDto dto = new PersonaEspecialidadFindAllDto();
    	dto.setId(entity.getId());
    	dto.setCodPersona(entity.getPersona().getIdPersona());
    	dto.setEsEntrenador(entity.getEsEntrenador());
    	dto.setEsDeportista(entity.getEsDeportista());
    	dto.setEsJuez(entity.getEsJuez());
    	dto.setFechaActivacion(entity.getFechaActivacion());
    	dto.setCodEspecialidad(entity.getEspecialidad().getIdEspecialidad());
    	dto.setNivel(entity.getNivel());
    	return dto;
    }
}
