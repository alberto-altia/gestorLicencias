package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.convertidor.ConversorClub;
import proyectoFCT.gestorLicencias.convertidor.ConversorPersona;
import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.domain.dto.DeportistasClubDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;
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
    public ClubDTO crearOuModificarClub(ClubDTO clubDTO) {
        conversorClub.validadNumLicenciaEntrenador(clubDTO);

        Club club = clubDTO.getIdClub() != null ? clubRepository.findById(clubDTO.getIdClub()).get() : new Club();
        BeanUtils.copyProperties(clubDTO, club, "idClub", "numLicenciaEntrenador","licenciaClub");
        club.setLicenciaClub(generarLicencias.generarCodigo());
        club = clubRepository.save(club);
        return conversorClub.toDto(club);
    }

    @Override
    public ClubDTO findClubByPersona(String id) {
        Long idConvertido = Long.parseLong(id);
        if(!personaRepository.existsPersonaByIdPersona(idConvertido))
            throw new BadRequestException("Id persona no existente");
        return conversorClub.toDto(clubRepository.findClubByPersonas(personaRepository.findPersonaByIdPersona(idConvertido)));
    }

    @Override
    public List<ClubDTO> findAll() {
        return  clubRepository.findAll()
                .stream()
                .map(conversorClub::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClubDTO> findClubByPersonaId(Long id) {
        return  clubRepository.findClubsByPersonas(personaRepository.findPersonaByIdPersona(id))
                .stream()
                .map(conversorClub::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeportistasClubDTO> findPersonaByIdClub(Long id) {
        if(!clubRepository.existsById(id))
            throw new BadRequestException("Id club no existente");
        return personaRepository.findPersonaByClub(clubRepository.findClubByIdClub(id))
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DeportistasClubDTO toDTO ( Persona entity){
        DeportistasClubDTO dto = new DeportistasClubDTO();
        dto.setNombreApellidos(entity.getNombreApellidos());
        dto.setDni(entity.getDNI());
        dto.setNumLicenciaDeportista(entity.getNumLicenciaDeportista());
        return dto;
    }
}
