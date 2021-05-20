package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.convertidor.ConvertidorClub;
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
    ConvertidorClub convertidorClub;

    @Override
    public ClubDTO crearClub(ClubDTO clubDTO){
        convertidorClub.validadNumLicenciaEntrenador(clubDTO);
        clubRepository.save(convertidorClub.convertirClubDTOtoEntity(clubDTO));
        return clubDTO;
    }
}
