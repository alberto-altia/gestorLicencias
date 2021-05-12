package proyectoFCT.gestorLicencias.controller.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.entity.Entrenador;
import proyectoFCT.gestorLicencias.repository.EntrenadorRepository;

@Component
public class ClubControllerHelper {
    @Autowired
    EntrenadorRepository entrenadorRepository;

    public Club convertirClubDTOtoEntity(ClubDTO clubDTO){
        Club club = new Club();
        club.setNombreClub(clubDTO.getNombreClub());
        club.setLicenciaClub(clubDTO.getNumLicenciaClub());
        club.setFechaCreacion(clubDTO.getFechaCreacion());
        club.setEntrenador(new Entrenador(clubDTO.getNumLicenciaEntrenador()));

        return club;
    }
}
