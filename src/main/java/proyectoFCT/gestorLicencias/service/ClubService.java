package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.domain.dto.DeportistasClubDTO;

import java.io.IOException;
import java.util.List;

public interface ClubService {

    ClubDTO crearOuModificarClub(ClubDTO clubDTO) throws IOException;

    List<ClubDTO> findAll();

    List<ClubDTO> findClubsByUsername(String username);

    List<DeportistasClubDTO> findPersonaByIdClub(Long id);
}
