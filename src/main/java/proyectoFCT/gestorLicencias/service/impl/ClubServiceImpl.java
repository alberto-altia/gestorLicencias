package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.aspects.AnotacionLogMetodos;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.convertidor.ConversorClub;
import proyectoFCT.gestorLicencias.convertidor.ConversorPersona;
import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.domain.dto.DeportistasClubDTO;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.entity.Persona;
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
    ConversorClub conversorClub;

    @Autowired
    ConversorPersona conversorPersona;

    @Autowired
    GenerarLicencias generarLicencias;

    @Override
    @AnotacionLogMetodos(operacion = "crear o modificar club")
    public ClubDTO crearOuModificarClub(ClubDTO clubDTO) {
        conversorClub.validadNumLicenciaEntrenador(clubDTO);

        Club club = clubDTO.getIdClub() != null ? clubRepository.findById(clubDTO.getIdClub()).get() : new Club();
        BeanUtils.copyProperties(clubDTO, club, "idClub", "numLicenciaEntrenador", "licenciaClub");
        club.setLicenciaClub(generarLicencias.generarCodigo());
        club = clubRepository.save(club);
        return conversorClub.toDto(club);
    }

    @Override
    @AnotacionLogMetodos(operacion = "findAllClubs")
    public List<ClubDTO> findAll() {
        return clubRepository.findAll()
                .stream()
                .map(conversorClub::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @AnotacionLogMetodos(operacion = "findClubByPersonaId")
    public List<ClubDTO> findClubsByUsername(String username) {
        return clubRepository.findClubsByPersonas(personaRepository.findPersonaByUsuario(username))
			                 .stream()
			                 .map(conversorClub::toDto)
			                 .collect(Collectors.toList());
    }

    @Override
    @AnotacionLogMetodos(operacion = "findPersonaByClubId")
    public List<DeportistasClubDTO> findPersonaByIdClub(Long id) {
        if (!clubRepository.existsById(id))
            throw new BadRequestException("Id club no existente");
        return personaRepository.findPersonaByClub(clubRepository.findClubByIdClub(id))
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DeportistasClubDTO toDTO(Persona entity) {
        DeportistasClubDTO dto = new DeportistasClubDTO();
        dto.setIdPersona(entity.getIdPersona());
        dto.setNombreApellidos(entity.getNombreApellidos());
        dto.setDni(entity.getDNI());
        dto.setNumLicenciaDeportista(entity.getNumLicenciaDeportista());
        return dto;
    }
}
