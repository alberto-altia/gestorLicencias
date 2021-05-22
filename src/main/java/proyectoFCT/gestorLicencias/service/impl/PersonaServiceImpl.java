package proyectoFCT.gestorLicencias.service.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectoFCT.gestorLicencias.convertidor.ConvertidorPersona;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.entity.Persona;
import proyectoFCT.gestorLicencias.repository.PersonaRepository;
import proyectoFCT.gestorLicencias.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService{
	@Autowired
	PersonaRepository personaRepository;
	
	@Autowired
	ConvertidorPersona convertidorPersona;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public PersonaDTO createOrUpdate(PersonaDTO input) {
		Persona persona = input.getIdPersona() != null ? personaRepository.findById(input.getIdPersona()).get() : new Persona(); 
		BeanUtils.copyProperties(input, persona, "codClub");
		persona.setClub(entityManager.getReference(Club.class, input.getCodClub()));
		return convertidorPersona.toDto(personaRepository.save(persona));
	}
}
