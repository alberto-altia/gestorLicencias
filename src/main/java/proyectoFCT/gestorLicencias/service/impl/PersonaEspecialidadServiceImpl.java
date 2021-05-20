package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.convertidor.ConvertidorPersona;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadDTO;
import proyectoFCT.gestorLicencias.entity.Persona;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;
import proyectoFCT.gestorLicencias.repository.IEspecialidadRepository;
import proyectoFCT.gestorLicencias.repository.PersonaEspecialidadRepository;
import proyectoFCT.gestorLicencias.service.IPersonaService;
import proyectoFCT.gestorLicencias.service.PersonaEspecialidadService;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonaEspecialidadServiceImpl implements PersonaEspecialidadService {

	@Autowired
	IEspecialidadRepository especialidadRepository;
	
	@Autowired
	EntityManager entityManager;
	
    @Autowired
    ConvertidorPersona convertidorPersona;
    
    @Autowired
    IPersonaService personaService;

    @Autowired
    PersonaEspecialidadRepository personaEspecialidadRepository;

    @Override
    @Transactional
    public PersonaEspecialidadDTO crearPersona(PersonaEspecialidadDTO personaEspecialidadDTO) {
    	if(!especialidadRepository.existsById(personaEspecialidadDTO.getEspecialidad().getIdEspecialidad()))
    		throw new EntityNotFoundException();
    	PersonaEspecialidad pEsp = new PersonaEspecialidad();
    	BeanUtils.copyProperties(personaEspecialidadDTO, pEsp);
    	
    	Long idPersona = personaService.createOrUpdate(personaEspecialidadDTO.getPersona()).getIdPersona();
    	System.out.println(idPersona);
    	
    	pEsp.setPersona(entityManager.getReference(Persona.class, idPersona));
    	
    	
    	//Persona persona = new Persona();    	
    	
        personaEspecialidadRepository.save(pEsp);
        return personaEspecialidadDTO;
    }

    @Override
    public List<PersonaEspecialidad> findAll() {
        return (List<PersonaEspecialidad>) personaEspecialidadRepository.findAll();
    }
}
