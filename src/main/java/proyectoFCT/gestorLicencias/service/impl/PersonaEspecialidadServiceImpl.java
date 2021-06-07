package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.aspects.AnotacionLogMetodos;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.convertidor.ConversorPersona;
import proyectoFCT.gestorLicencias.domain.dto.CrearLicenciaDTO;
import proyectoFCT.gestorLicencias.domain.dto.LicenciasActivasDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadFindAllDto;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;
import proyectoFCT.gestorLicencias.repository.EspecialidadRepository;
import proyectoFCT.gestorLicencias.repository.PersonaEspecialidadRepository;
import proyectoFCT.gestorLicencias.repository.PersonaRepository;
import proyectoFCT.gestorLicencias.service.PersonaEspecialidadService;
import proyectoFCT.gestorLicencias.service.PersonaService;

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

    @Override
    @AnotacionLogMetodos(operacion = "findAllPersonasEspecialidad")
    public List<PersonaEspecialidadFindAllDto> findAll() {
        return personaEspecialidadRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @AnotacionLogMetodos(operacion = "licenciasActivasByPersonaId")
    public List<LicenciasActivasDTO> licenciasActivasOf(Long codUsuario) {
        if (!personaRepository.existsPersonaByIdPersona(codUsuario))
            throw new BadRequestException("Id persona no existente (id = " + codUsuario + ")");
        return personaEspecialidadRepository.licenciasActivasOf(codUsuario)
                .stream()
                .map(this::toDtoLicencias)
                .collect(Collectors.toList());
    }
    
    @Override
    @AnotacionLogMetodos(operacion = "licenciasActivasByPersonaId")
    public List<LicenciasActivasDTO> licenciasActivas(String usuario) {
        if (!personaRepository.existsPersonaByUsuario(usuario))
            throw new BadRequestException("Id persona no existente (id = " + usuario + ")");
        return personaEspecialidadRepository.licenciasActivas(usuario)
                .stream()
                .map(this::toDtoLicencias)
                .collect(Collectors.toList());
    }

    @Override
    @AnotacionLogMetodos(operacion = "allLicencias")
    public List<LicenciasActivasDTO> todasLicencias() {
        return personaEspecialidadRepository.findAll()
                .stream()
                .map(this::toDtoLicencias)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @AnotacionLogMetodos(operacion = "crearNuevaLicencia")
    public CrearLicenciaDTO crearNuevaLicencia(CrearLicenciaDTO crearLicenciaDTO) {
        System.out.println(crearLicenciaDTO.getCodPersona() + " " + crearLicenciaDTO.getNombreEspecialidad());
        if (!personaRepository.existsPersonaByIdPersona(crearLicenciaDTO.getCodPersona()))
            throw new BadRequestException("Usuario no existente");
        if (personaEspecialidadRepository.existsPersonaEspecialidadByEspecialidadAndPersonaAndEsDeportistaAndAndEsEntrenadorAndEsJuez(especialidadRepository.findEspecialidadByNombreEspecialidad(crearLicenciaDTO.getNombreEspecialidad()), personaRepository.findPersonaByIdPersona(crearLicenciaDTO.getCodPersona()), crearLicenciaDTO.getEsDeportista(), crearLicenciaDTO.getEsEntrenador(), crearLicenciaDTO.getEsJuez()))
            throw new BadRequestException("Ya existe una licencia activada");

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

    @Override
    @Transactional
    @AnotacionLogMetodos(operacion = "eliminarLicencia")
    public void eliminarLicencia(Long id) {
        if (!personaEspecialidadRepository.existsPersonaEspecialidadById(id))
            throw new BadRequestException("Licencia no encontrada");
        personaEspecialidadRepository.deletePersonaEspecialidadById(id.longValue());
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
        dto.setIdLicencia(entity.getId());
        dto.setNombreEspecialidad(entity.getEspecialidad().getNombreEspecialidad());
        dto.setFechaActivacion(entity.getFechaActivacion());
        dto.setNivel(entity.getNivel());
        if (entity.getEsDeportista()) dto.setTipoLicencia("Deportista");
        if (entity.getEsEntrenador()) dto.setTipoLicencia("Entrenador");
        if (entity.getEsJuez()) dto.setTipoLicencia("Juez");
        return dto;
    }
}
