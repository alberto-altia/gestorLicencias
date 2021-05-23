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
import proyectoFCT.gestorLicencias.repository.PersonaRepository;
import proyectoFCT.gestorLicencias.service.IPersonaService;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

@Service
public class PersonaServiceImpl implements IPersonaService {
    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ConvertidorPersona convertidorPersona;

    @Autowired
    EntityManager entityManager;

    @Override
    public PersonaDTO createOrUpdate(PersonaDTO input) {
        Persona persona = input.getIdPersona() != null ? personaRepository.findById(input.getIdPersona()).get() : new Persona();
        BeanUtils.copyProperties(input, persona, "codClub");
        persona.setClub(entityManager.getReference(Club.class, input.getCodClub()));
        return convertidorPersona.toDto(personaRepository.save(persona));
    }

    @Override
    public PersonaDTO login(LoginDTO loginDTO) {
        if (!personaRepository.existsPersonaByUsuario(loginDTO.getUsuario())){
            System.out.println("usuario no registrado");
            throw new BadRequestException("Usuario no registrado");
        }
        if (!personaRepository.existsPersonaByUsuarioAndPassword(loginDTO.getUsuario(),loginDTO.getPassword())){
            System.out.println("contraseña incorrecta");
            throw new BadRequestException("Contraseña incorrecta");

        }
        return convertidorPersona.toDto(personaRepository.findPersonaByUsuarioAndPassword(loginDTO.getUsuario(), loginDTO.getPassword()));
    }

}
