package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;

import java.io.IOException;

public interface ClubService {

    ClubDTO crearOuModificarClub (ClubDTO clubDTO) throws IOException;
}
