package proyectoFCT.gestorLicencias.convertidor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.repository.PersonaRepository;

import java.io.IOException;

@Component
public class ConvertidorClub {
    @Autowired
    PersonaRepository personaRepository;

    public Club convertirClubDTOtoEntity(ClubDTO clubDTO) {
        Club club = new Club();
        club.setNombreClub(clubDTO.getNombreClub());
        club.setLicenciaClub(clubDTO.getNumLicenciaClub());
        club.setFechaCreacion(clubDTO.getFechaCreacion());

        return club;
    }

    public void validadNumLicenciaEntrenador(ClubDTO clubDTO) {
        String numLicenciaEntrenador = null;
        numLicenciaEntrenador = clubDTO.getNumLicenciaEntrenador();
        personaRepository.findNumLicenciaEntrenador(numLicenciaEntrenador);
        System.out.println(personaRepository.findNumLicenciaEntrenador(numLicenciaEntrenador));
        if (numLicenciaEntrenador.equals(personaRepository.findNumLicenciaEntrenador(numLicenciaEntrenador))) {
            System.out.println("validacion correcta");
        } else {
            System.out.println("validacion incorrecta");
        }
    }
}
