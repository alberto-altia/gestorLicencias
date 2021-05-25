package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.convertidor.ConvertidorClub;
import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.repository.ClubRepository;
import proyectoFCT.gestorLicencias.repository.PersonaRepository;
import proyectoFCT.gestorLicencias.service.ClubService;
import proyectoFCT.gestorLicencias.utils.GenerarLicencias;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ConvertidorClub convertidorClub;

    @Autowired
    GenerarLicencias generarLicencias;

    @Override
    public ClubDTO crearOuModificarClub(ClubDTO clubDTO) {
        convertidorClub.validadNumLicenciaEntrenador(clubDTO);

        Club club = clubDTO.getIdClub() != null ? clubRepository.findById(clubDTO.getIdClub()).get() : new Club();
        BeanUtils.copyProperties(clubDTO, club, "idClub", "numLicenciaEntrenador","licenciaClub");
        club.setLicenciaClub(generarLicencias.generarCodigo());
        club = clubRepository.save(club);
        return convertidorClub.toDto(club);
    }

    @Override
    public ClubDTO findClubByPersona(String id) {
        Long idConvertido = Long.parseLong(id);
        if(!personaRepository.existsPersonaByIdPersona(idConvertido))
            throw new BadRequestException("Id persona no existente");
        return convertidorClub.toDto(clubRepository.findClubByPersonas(personaRepository.findPersonaByIdPersona(idConvertido)));
    }

    @Override
    public List<ClubDTO> findAll() {
        return  clubRepository.findAll()
                .stream()
                .map(convertidorClub::toDto)
                .collect(Collectors.toList());
    }


}
