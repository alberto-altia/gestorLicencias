package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.convertidor.ConvertidorPersona;
import proyectoFCT.gestorLicencias.domain.dto.LoginDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.entity.Persona;
import proyectoFCT.gestorLicencias.repository.PersonaEspecialidadRepository;
import proyectoFCT.gestorLicencias.repository.PersonaRepository;
import proyectoFCT.gestorLicencias.service.IPersonaService;
import proyectoFCT.gestorLicencias.utils.GenerarLicencias;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class PersonaServiceImpl implements IPersonaService {
    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PersonaEspecialidadRepository personaEspecialidadRepository;

    @Autowired
    ConvertidorPersona convertidorPersona;

    @Autowired
    EntityManager entityManager;

    @Autowired
    GenerarLicencias generarLicencias;

    @Override
    public PersonaDTO createOrUpdate(PersonaDTO input, Boolean esEntrenador, Boolean esDeportista, Boolean esJuez) {
       if(!input.getUsuario().equals(personaRepository.findPersonaByIdPersona(input.getIdPersona()).getUsuario())){
            if(personaRepository.existsPersonaByUsuario(input.getUsuario()))
                throw new BadRequestException("Ese nombre de usuario ya existe");
       }
        Persona persona = input.getIdPersona() != null ? personaRepository.findById(input.getIdPersona()).get() : new Persona();
        BeanUtils.copyProperties(input, persona, "codClub","numLicenciaDeportista","numLicenciaEntrenador","numLicenciaJuez");
        persona.setClub(entityManager.getReference(Club.class, input.getCodClub()));
        if(esDeportista) persona.setNumLicenciaDeportista(generarLicencias.generarCodigo());
        if(esEntrenador) persona.setNumLicenciaEntrenador(generarLicencias.generarCodigo());
        if (esJuez) persona.setNumLicenciaJuez(generarLicencias.generarCodigo());

        return convertidorPersona.toDto(personaRepository.save(persona));
    }

    @Override
    public PersonaDTO Update(PersonaDTO input) {
        if(!input.getUsuario().equals(personaRepository.findPersonaByIdPersona(input.getIdPersona()).getUsuario())){
            if(personaRepository.existsPersonaByUsuario(input.getUsuario()))
                throw new BadRequestException("Ese nombre de usuario ya existe");
        }
        Persona persona = input.getIdPersona() != null ? personaRepository.findById(input.getIdPersona()).get() : new Persona();
        BeanUtils.copyProperties(input, persona, "codClub","numLicenciaDeportista","numLicenciaEntrenador","numLicenciaJuez");
        persona.setClub(entityManager.getReference(Club.class, input.getCodClub()));

        return convertidorPersona.toDto(personaRepository.save(persona));
    }

    @Override
    public PersonaDTO login(LoginDTO loginDTO) {
        if (!personaRepository.existsPersonaByUsuario(loginDTO.getUsuario())){
            throw new BadRequestException("Usuario no registrado");
        }
        if (!personaRepository.existsPersonaByUsuarioAndPassword(loginDTO.getUsuario(),loginDTO.getPassword())){
            throw new BadRequestException("Contraseña incorrecta");
        }
        return convertidorPersona.toDto(personaRepository.findPersonaByUsuarioAndPassword(loginDTO.getUsuario(), loginDTO.getPassword()));
    }

    public PersonaDTO findPersonaById(String id){
        Long idConvertido = Long.parseLong(id);
        System.out.println(idConvertido);
        if(!personaRepository.existsPersonaByIdPersona(idConvertido))
            throw new BadRequestException("la persona con id: " + idConvertido + " no existe");
        return convertidorPersona.toDto(personaRepository.findPersonaByIdPersona(idConvertido));
    }
    
    @Transactional
    public Long delete(String idParam){
        Long id = Long.parseLong(idParam);
        personaEspecialidadRepository.deletePersonaEspecialidadByPersona(personaRepository.findPersonaByIdPersona(id));
        personaRepository.deleteById(id);
        return id;
    }



}
