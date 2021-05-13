package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.controller.helper.ClubControllerHelper;
import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.repository.ClubRepository;
import proyectoFCT.gestorLicencias.service.ClubService;

import java.io.IOException;

@Service
public class
ClubServiceImpl implements ClubService {

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    ClubControllerHelper clubControllerHelper;

    @Override
    public ClubDTO crearClub(ClubDTO clubDTO) throws IOException {
        clubControllerHelper.validadNumLicenciaEntrenador(clubDTO);
        clubRepository.save(clubControllerHelper.convertirClubDTOtoEntity(clubDTO));
        return clubDTO;
    }
}
