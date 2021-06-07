package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import proyectoFCT.gestorLicencias.Config.JwtHelper;
import proyectoFCT.gestorLicencias.aspects.AnotacionLogMetodos;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.convertidor.ConversorPersona;
import proyectoFCT.gestorLicencias.domain.dto.JwtDto;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
	
	@Autowired
	JwtHelper jwtHelper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

    @Override
    @AnotacionLogMetodos(operacion = "crearDeportista")
    public PersonaDTO create(PersonaDTO input) {
        if(personaRepository.existsPersonaByUsuario(input.getUsuario()))
            throw new BadRequestException("Usuario existente");
        Persona persona = input.getIdPersona() != null ? personaRepository.findById(input.getIdPersona()).get() : new Persona();
        BeanUtils.copyProperties(input, persona);
        persona.setNumLicenciaDeportista(generarLicencias.generarCodigo());
        persona.setClub(clubRepository.findClubByIdClub(input.getCodClub()));
        persona.setPassword(passwordEncoder.encode(input.getPassword()));;
        return conversorPersona.toDto(personaRepository.save(persona));
    }

    @Override
    @AnotacionLogMetodos(operacion = "crearEntrenador")
    public PersonaDTO createEntrenador(PersonaDTO input) {
        if(personaRepository.existsPersonaByUsuario(input.getUsuario()))
            throw new BadRequestException("Usuario existente");
        Persona persona = input.getIdPersona() != null ? personaRepository.findById(input.getIdPersona()).get() : new Persona();
        BeanUtils.copyProperties(input, persona);
        persona.setNumLicenciaEntrenador(generarLicencias.generarCodigo());
        persona.setClub(clubRepository.findClubByIdClub(input.getCodClub()));

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
    public PersonaDTO update(PersonaDTO input) {
        if (!input.getUsuario().equals(personaRepository.findPersonaByIdPersona(input.getIdPersona()).getUsuario())) {
            if (personaRepository.existsPersonaByUsuario(input.getUsuario()))
                throw new BadRequestException("Ese nombre de usuario ya existe");
        }
        Persona persona = input.getIdPersona() != null ? personaRepository.findById(input.getIdPersona()).get() : new Persona();
        BeanUtils.copyProperties(input, persona, "codClub", "numLicenciaDeportista", "numLicenciaEntrenador", "numLicenciaJuez");
        persona.setClub(entityManager.getReference(Club.class, input.getCodClub()));
        persona.setNumLicenciaDeportista(generarLicencias.generarCodigo());

        return conversorPersona.toDto(personaRepository.save(persona));
    }

    @Override
    @AnotacionLogMetodos(operacion = "login")
    public JwtDto login(LoginDTO loginDTO) {
    	Persona persona = personaRepository.findPersonaByUsuario(loginDTO.getUsuario());
        if(persona == null) {
        	throw new BadRequestException("Usuario no registrado");
        }
        
        if(!passwordEncoder.matches(loginDTO.getPassword(), persona.getPassword()))
        	throw new BadRequestException("Contraseña incorrecta");
		
		Map<String, String> claims = new HashMap<>();
		claims.put("username", loginDTO.getUsuario());
		claims.put("userId", persona.getIdPersona().toString());
		
		JwtDto jwtDto = new JwtDto(jwtHelper.createJwtForClaims(persona.getUsuario(), claims));
        
        return jwtDto;
    }

    @AnotacionLogMetodos(operacion = "findPersonaById")
    public PersonaDTO findPersonaByUsername(String id) {
        if (!personaRepository.existsPersonaByUsuario(id))
            throw new BadRequestException("la persona con id: " + id + " no existe");
        return conversorPersona.toDto(personaRepository.findPersonaByUsuario(id));
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
