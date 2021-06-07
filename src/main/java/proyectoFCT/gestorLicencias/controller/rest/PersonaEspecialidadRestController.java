package proyectoFCT.gestorLicencias.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.domain.dto.CrearLicenciaDTO;
import proyectoFCT.gestorLicencias.domain.dto.LicenciasActivasDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadFindAllDto;
import proyectoFCT.gestorLicencias.service.impl.PersonaEspecialidadServiceImpl;

import javax.persistence.EntityNotFoundException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonaEspecialidadRestController {

    @Autowired
    PersonaEspecialidadServiceImpl personaEspecialidadService;

    @GetMapping("/personasEspecialidad")
    public ResponseEntity<List<PersonaEspecialidadFindAllDto>> obtenerPersonas() {
        return ResponseEntity.ok(personaEspecialidadService.findAll());
    }

    @PostMapping("/nuevaLicencia")
    public ResponseEntity<CrearLicenciaDTO> crearLicencia(@RequestBody CrearLicenciaDTO crearLicenciaDTO) {
        return ResponseEntity.ok(personaEspecialidadService.crearNuevaLicencia(crearLicenciaDTO));
    }

    @GetMapping("/licencias/{id}")
    public ResponseEntity<List<LicenciasActivasDTO>> obtenerLicencias(@PathVariable Long id) {
        return ResponseEntity.ok(personaEspecialidadService.licenciasActivasOf(id));
    }
    
    @GetMapping("/mis-licencias")
    public ResponseEntity<List<LicenciasActivasDTO>> obtenerLicencias(Principal principal) {
        return ResponseEntity.ok(personaEspecialidadService.licenciasActivas(principal.getName()));
    }

    @GetMapping("/licencias")
    public ResponseEntity<List<LicenciasActivasDTO>> obtenerAllLicencias() {
        return ResponseEntity.ok(personaEspecialidadService.todasLicencias());
    }

    @DeleteMapping("eliminarLicencias/{id}")
    public ResponseEntity<?> eliminarLicencia(@PathVariable Long id) {
        try {
            personaEspecialidadService.eliminarLicencia(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            throw new BadRequestException("id incorrecto");
        }
        return ResponseEntity.ok("Licencia eliminada correctamente");
    }
}
