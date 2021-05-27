package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.convertidor.ConversorPersona;
import proyectoFCT.gestorLicencias.domain.dto.*;
import proyectoFCT.gestorLicencias.entity.Especialidad;
import proyectoFCT.gestorLicencias.entity.Persona;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;
import proyectoFCT.gestorLicencias.repository.EspecialidadRepository;
import proyectoFCT.gestorLicencias.repository.PersonaEspecialidadRepository;
import proyectoFCT.gestorLicencias.repository.PersonaRepository;
import proyectoFCT.gestorLicencias.service.PersonaService;
import proyectoFCT.gestorLicencias.service.PersonaEspecialidadService;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaEspecialidadServiceImpl implements PersonaEspecialidadService {

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ConversorPersona conversorPersona;

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PersonaEspecialidadRepository personaEspecialidadRepository;

    @Transactional
    public PersonaEspecialidadCrearDTO crearDeportista(PersonaEspecialidadCrearDTO deportista) {
        //Especialidad non existe
        if (!especialidadRepository.existsById(deportista.getCodEspecialidad()))
            throw new BadRequestException("Codigo especialidad no valido");
        //Usuario collido
        if (personaRepository.existsPersonaByUsuario(deportista.getPersona().getUsuario()))
            throw new BadRequestException("Usuario existente");

        PersonaEspecialidad pEsp = new PersonaEspecialidad();
        BeanUtils.copyProperties(deportista, pEsp,"fechaActivacion");

        LocalDate now = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        pEsp.setFechaActivacion(now.format(format));
        pEsp.setEsJuez(false);
        pEsp.setEsDeportista(true);
        pEsp.setEsEntrenador(false);
        pEsp.setPersona(entityManager.getReference(Persona.class, personaService.create(deportista.getPersona(), false, true, false).getIdPersona()));
        pEsp.setEspecialidad(entityManager.getReference(Especialidad.class, deportista.getCodEspecialidad()));
        personaEspecialidadRepository.save(pEsp);
        return deportista;
    }

    @Transactional
    public PersonaEspecialidadCrearDTO crearEntrenador(PersonaEspecialidadCrearDTO entrenador) {
        //Especialidad non existe
        if (!especialidadRepository.existsById(entrenador.getCodEspecialidad()))
            throw new BadRequestException("Codigo especialidad no valido");
        //Usuario collido
        if (personaRepository.existsPersonaByUsuario(entrenador.getPersona().getUsuario()))
            throw new BadRequestException("Usuario existente");

        PersonaEspecialidad pEsp = new PersonaEspecialidad();
        BeanUtils.copyProperties(entrenador, pEsp);
        pEsp.setEsJuez(false);
        pEsp.setEsDeportista(false);
        pEsp.setEsEntrenador(true);
        pEsp.setPersona(entityManager.getReference(Persona.class, personaService.create(entrenador.getPersona(), true, false, false).getIdPersona()));
        pEsp.setEspecialidad(entityManager.getReference(Especialidad.class, entrenador.getCodEspecialidad()));
        personaEspecialidadRepository.save(pEsp);
        return entrenador;
    }

    @Transactional
    public PersonaEspecialidadCrearDTO crearJuez(PersonaEspecialidadCrearDTO juez) {
        //Especialidad non existe
        if (!especialidadRepository.existsById(juez.getCodEspecialidad()))
            throw new BadRequestException("Codigo especialidad no valido");
        //Usuario collido
        if (personaRepository.existsPersonaByUsuario(juez.getPersona().getUsuario()))
            throw new BadRequestException("Usuario existente");

        PersonaEspecialidad pEsp = new PersonaEspecialidad();
        BeanUtils.copyProperties(juez, pEsp);
        pEsp.setEsJuez(true);
        pEsp.setEsDeportista(false);
        pEsp.setEsEntrenador(false);
        pEsp.setPersona(entityManager.getReference(Persona.class, personaService.create(juez.getPersona(), false, false, true).getIdPersona()));
        pEsp.setEspecialidad(entityManager.getReference(Especialidad.class, juez.getCodEspecialidad()));
        personaEspecialidadRepository.save(pEsp);
        return juez;
    }

    @Override
    public List<PersonaEspecialidadFindAllDto> findAll() {
        return personaEspecialidadRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LicenciasActivasDTO> licenciasActivas(Long idPersona) {
         Long id = 1L;
        return personaEspecialidadRepository.licenciasActivas(id)
                .stream()
                .map(this::toDtoLicencias)
                .collect(Collectors.toList());
    }

    @Override
    public List<LicenciasActivasDTO> todasLicencias() {
        return personaEspecialidadRepository.findAll()
                .stream()
                .map(this::toDtoLicencias)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CrearLicenciaDTO crearNuevaLicencia(CrearLicenciaDTO crearLicenciaDTO) {
        if(!personaRepository.existsPersonaByIdPersona(crearLicenciaDTO.getCodPersona()))
            throw new BadRequestException("Usuario no existente");

        PersonaEspecialidad personaEspecialidad = new PersonaEspecialidad();
        personaEspecialidad.setEspecialidad(especialidadRepository.findEspecialidadByNombreEspecialidad(crearLicenciaDTO.getNombreEspecialidad()));
        personaEspecialidad.setPersona(personaRepository.findPersonaByIdPersona(crearLicenciaDTO.getCodPersona()));
        personaEspecialidad.setNivel(crearLicenciaDTO.getNivel());

        LocalDate now = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        personaEspecialidad.setFechaActivacion(now.format(format));

        personaEspecialidad.setEsDeportista(crearLicenciaDTO.getEsDeportista());
        personaEspecialidad.setEsEntrenador(crearLicenciaDTO.getEsEntrenador());
        personaEspecialidad.setEsJuez(crearLicenciaDTO.getEsJuez());
        personaEspecialidadRepository.save(personaEspecialidad);
        return crearLicenciaDTO;
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
    public LicenciasActivasDTO toDtoLicencias(PersonaEspecialidad entity) {
        LicenciasActivasDTO dto = new LicenciasActivasDTO();
        dto.setNombreEspecialidad(entity.getEspecialidad().getNombreEspecialidad());
        dto.setFechaActivacion(entity.getFechaActivacion());
        dto.setNivel(entity.getNivel());
        return dto;
    }
}
