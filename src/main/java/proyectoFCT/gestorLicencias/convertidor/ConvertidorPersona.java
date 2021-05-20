package proyectoFCT.gestorLicencias.convertidor;

import org.springframework.stereotype.Component;

import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadDTO;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.entity.Especialidad;
import proyectoFCT.gestorLicencias.entity.Persona;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;

@Component
public class ConvertidorPersona {

    public PersonaEspecialidad convertirPersonaEspecialidadDTOtoEntity(PersonaEspecialidadDTO personaEspecialidadDTO)  {
        PersonaEspecialidad personaEspecialidad = new PersonaEspecialidad();
        personaEspecialidad.setEsDeportista(personaEspecialidadDTO.getEsDeportista());
        personaEspecialidad.setEsEntrenador(personaEspecialidadDTO.getEsEntrenador());
        personaEspecialidad.setEsJuez(personaEspecialidadDTO.getEsJuez());
        personaEspecialidad.setFechaActivacion(personaEspecialidadDTO.getFechaActivacion());
        personaEspecialidad.setNivel(personaEspecialidadDTO.getNivel());

        return personaEspecialidad;
    }
    
    public PersonaDTO toDto(Persona entity) {
    	PersonaDTO dto = new PersonaDTO();
    	dto.setIdPersona(entity.getIdPersona());
    	dto.setNombreApellidos(entity.getNombreApellidos());
    	dto.setFechaNacimiento(entity.getFechaNacimiento());
    	dto.setTelefono(entity.getTelefono());
    	dto.setEmail(entity.getEmail());
    	dto.setNumLicenciaDeportista(entity.getNumLicenciaDeportista());
    	dto.setNumLicenciaEntrenador(entity.getNumLicenciaEntrenador());
    	dto.setNumLicenciaJuez(entity.getNumLicenciaJuez());
    	dto.setCodClub(entity.getClub().getIdClub());
    	return dto;
    }
    
    public Persona getPersonaFromDto(PersonaDTO dto){
        Persona persona = new Persona();

        persona.setIdPersona(dto.getIdPersona());
        persona.setNombreApellidos(dto.getNombreApellidos());
        persona.setFechaNacimiento(dto.getFechaNacimiento());
        persona.setTelefono(dto.getTelefono());
        persona.setEmail(dto.getEmail());
        persona.setNumLicenciaDeportista(dto.getNumLicenciaDeportista());
        persona.setNumLicenciaEntrenador(dto.getNumLicenciaEntrenador());
        persona.setNumLicenciaJuez(dto.getNumLicenciaJuez());
        //persona.setPersonaEspecialidad(null);
//        persona.setClub(new Club(personaEspecialidadDTO.getClub().getIdClub(),
//                                personaEspecialidadDTO.getClub().getLicenciaClub(),
//                                personaEspecialidadDTO.getClub().getNombreClub(),
//                                personaEspecialidadDTO.getClub().getFechaCreacion()));
        persona.setDNI(dto.getDni());
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