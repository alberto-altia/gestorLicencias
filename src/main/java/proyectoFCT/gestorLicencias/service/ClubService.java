package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.domain.dto.DeportistasClubDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;
import proyectoFCT.gestorLicencias.entity.Persona;

import java.io.IOException;
import java.util.List;

public interface ClubService {

    ClubDTO crearOuModificarClub (ClubDTO clubDTO) throws IOException;

    ClubDTO findClubByPersona (String id);

    List<ClubDTO> findAll();

    List<ClubDTO> findClubByPersonaId(Long id);

    List<DeportistasClubDTO> findPersonaByIdClub(Long id);
}
