package proyectoFCT.gestorLicencias.convertidor;

import org.springframework.stereotype.Component;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadDTO;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.entity.Especialidad;
import proyectoFCT.gestorLicencias.entity.Persona;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;

@Component
public class ConvertidorPersona {

    public PersonaEspecialidad convertirPersonaEspecialidadDTOtoEntity(PersonaEspecialidadDTO personaEspecialidadDTO)  {
        PersonaEspecialidad personaEspecialidad = new PersonaEspecialidad();

        personaEspecialidad.setPersona(convertirPersonaEspecialidadEnPersona(personaEspecialidadDTO));
        personaEspecialidad.setEspecialidad(convertirPersonaEspecialidadEnEspecialidad(personaEspecialidadDTO));
        personaEspecialidad.setEsDeportista(personaEspecialidadDTO.getEsDeportista());
        personaEspecialidad.setEsEntrenador(personaEspecialidadDTO.getEsEntrenador());
        personaEspecialidad.setEsJuez(personaEspecialidadDTO.getEsJuez());
        personaEspecialidad.setFechaActivacion(personaEspecialidadDTO.getFechaActivacion());
        personaEspecialidad.setNivel(personaEspecialidadDTO.getNivel());

        return personaEspecialidad;
    }
    public Persona convertirPersonaEspecialidadEnPersona(PersonaEspecialidadDTO personaEspecialidadDTO){
        Persona persona = new Persona();

        persona.setIdPersona(personaEspecialidadDTO.getPersona().getIdPersona());
        persona.setNombreApellidos(personaEspecialidadDTO.getPersona().getNombreApellidos());
        persona.setFechaNacimiento(personaEspecialidadDTO.getPersona().getFechaNacimiento());
        persona.setTelefono(personaEspecialidadDTO.getPersona().getTelefono());
        persona.setEmail(personaEspecialidadDTO.getPersona().getEmail());
        persona.setNumLicenciaDeportista(personaEspecialidadDTO.getPersona().getNumLicenciaDeportista());
        persona.setNumLicenciaEntrenador(personaEspecialidadDTO.getPersona().getNumLicenciaEntrenador());
        persona.setNumLicenciaJuez(personaEspecialidadDTO.getPersona().getNumLicenciaJuez());
        persona.setPersonaEspecialidad(null);
        persona.setClub(new Club(personaEspecialidadDTO.getPersona().getClub().getIdClub(),
                                personaEspecialidadDTO.getPersona().getClub().getLicenciaClub(),
                                personaEspecialidadDTO.getPersona().getClub().getNombreClub(),
                                personaEspecialidadDTO.getPersona().getClub().getFechaCreacion()));
        persona.setDNI(personaEspecialidadDTO.getPersona().getDni());
        return persona;
    }

    public Especialidad convertirPersonaEspecialidadEnEspecialidad(PersonaEspecialidadDTO personaEspecialidadDTO){
        Especialidad especialidad = new Especialidad();

        especialidad.setIdEspecialidad(personaEspecialidadDTO.getEspecialidad().getIdEspecialidad());
        especialidad.setNombre(personaEspecialidadDTO.getEspecialidad().getNombre());
        especialidad.setPersonaEspecialidad(null);

        return especialidad;
    }

}
// personaEspecialidadDTO.getEspecialidadDTO().getIdEspecialidad(),personaEspecialidadDTO.getEspecialidadDTO().getNombre()