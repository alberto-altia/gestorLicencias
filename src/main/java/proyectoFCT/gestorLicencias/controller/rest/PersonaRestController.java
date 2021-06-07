package proyectoFCT.gestorLicencias.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.domain.dto.JwtDto;
import proyectoFCT.gestorLicencias.domain.dto.LoginDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;
import proyectoFCT.gestorLicencias.service.PersonaEspecialidadService;
import proyectoFCT.gestorLicencias.service.impl.PersonaServiceImpl;

import javax.persistence.EntityNotFoundException;

import java.security.Principal;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonaRestController {

    @Autowired
    PersonaEspecialidadService personaEspecialidadService;

    @Autowired
    PersonaServiceImpl personaService;

    @GetMapping("/datos-persona")
    public ResponseEntity<PersonaDTO> obtenerPersonaById(Principal principal) {
        try {
            return ResponseEntity.ok(personaService.findPersonaByUsername(principal.getName()));
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("id incorrecto");
        }
    }

    @GetMapping("/personas")
    public ResponseEntity<List> allPersonas() {
        try {
            return ResponseEntity.ok(personaService.allPersonas());
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("id incorrecto");
        }
    }

    @PostMapping("/editarPersona")
    public ResponseEntity<PersonaDTO> editarPersona(@RequestBody PersonaDTO personaDTO) {
        try {
            return ResponseEntity.ok(personaService.update(personaDTO));
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("id incorrecto");
        }
    }

    @PostMapping("/nuevoDeportista")
    public ResponseEntity<PersonaDTO> crearDeportista(@RequestBody PersonaDTO personaDTO) {
        try {
            return ResponseEntity.ok(personaService.create(personaDTO));
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("error crear");
        }
    }

    @DeleteMapping("/eliminarPersona/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable String id) {
        try {
            return ResponseEntity.ok(personaService.delete(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new BadRequestException("Error al eliminar elemento");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> nueva(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(personaService.login(loginDTO));
        } catch (EntityNotFoundException e) {
            throw new BadRequestException("error endpoint");
        }
    }

//asdf
}
