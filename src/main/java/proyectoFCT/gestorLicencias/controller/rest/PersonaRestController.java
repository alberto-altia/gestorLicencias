package proyectoFCT.gestorLicencias.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.domain.dto.LoginDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadFindAllDto;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;
import proyectoFCT.gestorLicencias.service.PersonaEspecialidadService;
import proyectoFCT.gestorLicencias.service.impl.PersonaServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonaRestController {

    @Autowired
    PersonaEspecialidadService personaEspecialidadService;

    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping("/nuevaPersona")
    public ResponseEntity<PersonaEspecialidadDTO> nueva(@RequestBody PersonaEspecialidadDTO personaEspecialidadDTO) throws IOException {
        personaEspecialidadService.crearPersona(personaEspecialidadDTO);
        return ResponseEntity.ok(personaEspecialidadDTO);
    }

    @GetMapping("/personas")
    public ResponseEntity<List<PersonaEspecialidadFindAllDto>> obtenerPersonas() {
        return ResponseEntity.ok(personaEspecialidadService.findAll());
    }
    @PostMapping("/login")
    public ResponseEntity<?> nueva(@RequestBody LoginDTO loginDTO) {
        try{
        	return ResponseEntity.ok(personaService.login(loginDTO));
        }catch(EntityNotFoundException e) {
        	throw new BadRequestException("error endpoint");
        }
    }
//asdf
}
