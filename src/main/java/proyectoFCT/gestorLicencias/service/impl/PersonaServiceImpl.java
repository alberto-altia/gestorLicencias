package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.aspects.AnotacionLogMetodos;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.convertidor.ConversorPersona;
import proyectoFCT.gestorLicencias.domain.dto.LoginDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.entity.Persona;
import proyectoFCT.gestorLicencias.repository.ClubRepository;
import proyectoFCT.gestorLicencias.repository.PersonaEspecialidadRepository;
import proyectoFCT.gestorLicencias.repository.PersonaRepository;
import proyectoFCT.gestorLicencias.service.PersonaService;
import proyectoFCT.gestorLicencias.utils.GenerarLicencias;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PersonaEspecialidadRepository personaEspecialidadRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    ConversorPersona conversorPersona;

    @Autowired
    EntityManager entityManager;

    @Autowired
    GenerarLicencias generarLicencias;

    @Override
    @AnotacionLogMetodos(operacion = "crearPersona")
    public PersonaDTO create(PersonaDTO input, Boolean esEntrenador, Boolean esDeportista, Boolean esJuez) {
        Persona persona = input.getIdPersona() != null ? personaRepository.findById(input.getIdPersona()).get() : new Persona();
        BeanUtils.copyProperties(input, persona);
        persona.setClub(clubRepository.findClubByIdClub(input.getCodClub()));
        if (esDeportista) persona.setNumLicenciaDeportista(generarLicencias.generarCodigo());
        if (esEntrenador) persona.setNumLicenciaEntrenador(generarLicencias.generarCodigo());
        if (esJuez) persona.setNumLicenciaJuez(generarLicencias.generarCodigo());

        return conversorPersona.toDto(personaRepository.save(persona));
    }

    @Override
    @AnotacionLogMetodos(operacion = "allPersonas")
    public List<PersonaDTO> allPersonas() {
        return personaRepository.findAll()
                .stream()
                .map(this::personaToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @AnotacionLogMetodos(operacion = "updatePersona")
    public PersonaDTO Update(PersonaDTO input) {
        if (!input.getUsuario().equals(personaRepository.findPersonaByIdPersona(input.getIdPersona()).getUsuario())) {
            if (personaRepository.existsPersonaByUsuario(input.getUsuario()))
                throw new BadRequestException("Ese nombre de usuario ya existe");
        }
        Persona persona = input.getIdPersona() != null ? personaRepository.findById(input.getIdPersona()).get() : new Persona();
        BeanUtils.copyProperties(input, persona, "codClub", "numLicenciaDeportista", "numLicenciaEntrenador", "numLicenciaJuez");
        persona.setClub(entityManager.getReference(Club.class, input.getCodClub()));

        return conversorPersona.toDto(personaRepository.save(persona));
    }

    @Override
    @AnotacionLogMetodos(operacion = "login")
    public PersonaDTO login(LoginDTO loginDTO) {
        if (!personaRepository.existsPersonaByUsuario(loginDTO.getUsuario())) {
            throw new BadRequestException("Usuario no registrado");
        }
        if (!personaRepository.existsPersonaByUsuarioAndPassword(loginDTO.getUsuario(), loginDTO.getPassword())) {
            throw new BadRequestException("Contraseña incorrecta");
        }
        return conversorPersona.toDto(personaRepository.findPersonaByUsuarioAndPassword(loginDTO.getUsuario(), loginDTO.getPassword()));
    }

    @AnotacionLogMetodos(operacion = "findPersonaById")
    public PersonaDTO findPersonaById(String id) {
        Long idConvertido = Long.parseLong(id);
        System.out.println(idConvertido);
        if (!personaRepository.existsPersonaByIdPersona(idConvertido))
            throw new BadRequestException("la persona con id: " + idConvertido + " no existe");
        return conversorPersona.toDto(personaRepository.findPersonaByIdPersona(idConvertido));
    }

    @Transactional
    @AnotacionLogMetodos(operacion = "deletePersona")
    public Long delete(String idParam) {
        Long id = Long.parseLong(idParam);
        personaEspecialidadRepository.deletePersonaEspecialidadByPersona(personaRepository.findPersonaByIdPersona(id));
        personaRepository.deleteById(id);
        return id;
    }

    public PersonaDTO personaToDTO(Persona entity) {
        PersonaDTO dto = new PersonaDTO();

        dto.setIdPersona(entity.getIdPersona());
        dto.setNombreApellidos(entity.getNombreApellidos());
        dto.setDNI(entity.getDNI());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setTelefono(entity.getTelefono());
        dto.setEmail(entity.getEmail());
        dto.setNumLicenciaDeportista(entity.getNumLicenciaDeportista());
        dto.setNumLicenciaEntrenador(entity.getNumLicenciaEntrenador());
        dto.setNumLicenciaJuez(entity.getNumLicenciaJuez());
        dto.setCodClub(entity.getClub().getIdClub());
        dto.setUsuario(entity.getUsuario());
        dto.setPassword(entity.getPassword());

        return dto;
    }
    //todo dejar comentado json para crear personas


}
