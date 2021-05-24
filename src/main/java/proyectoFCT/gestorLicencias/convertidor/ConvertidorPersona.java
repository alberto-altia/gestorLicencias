package proyectoFCT.gestorLicencias.convertidor;

import org.springframework.stereotype.Component;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;
import proyectoFCT.gestorLicencias.entity.Persona;

@Component
public class ConvertidorPersona {

    public PersonaDTO toDto(Persona entity) {
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

}
// personaEspecialidadDTO.getEspecialidadDTO().getIdEspecialidad(),personaEspecialidadDTO.getEspecialidadDTO().getNombre()