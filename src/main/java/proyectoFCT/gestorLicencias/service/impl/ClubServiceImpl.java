package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.convertidor.ConvertidorClub;
import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.repository.ClubRepository;
import proyectoFCT.gestorLicencias.service.ClubService;

import javax.persistence.EntityManager;

@Service
public class
ClubServiceImpl implements ClubService {

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ConvertidorClub convertidorClub;

    @Override
    public ClubDTO crearOuModificarClub(ClubDTO clubDTO) {
        convertidorClub.validadNumLicenciaEntrenador(clubDTO);

        Club club = clubDTO.getIdClub() != null ? clubRepository.findById(clubDTO.getIdClub()).get() : new Club();

        BeanUtils.copyProperties(clubDTO, club, "idClub", "numLicenciaEntrenador");

        Long a = 5L;
        club = clubRepository.save(club);
        return convertidorClub.toDto(club);
    }
}
