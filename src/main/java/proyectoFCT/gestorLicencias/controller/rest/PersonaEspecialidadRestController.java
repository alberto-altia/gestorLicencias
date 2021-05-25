package proyectoFCT.gestorLicencias.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoFCT.gestorLicencias.domain.dto.CrearLicenciaDTO;
import proyectoFCT.gestorLicencias.domain.dto.LicenciasActivasDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadCrearDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadFindAllDto;
import proyectoFCT.gestorLicencias.service.impl.PersonaEspecialidadServiceImpl;

import java.util.List;

@RestController
public class PersonaEspecialidadRestController {

    @Autowired
    PersonaEspecialidadServiceImpl personaEspecialidadService;

    @PostMapping("/nuevoDeportista")
    public ResponseEntity<PersonaEspecialidadCrearDTO> nuevoDeportista(@RequestBody PersonaEspecialidadCrearDTO personaEspecialidadDTO) {
        personaEspecialidadService.crearDeportista(personaEspecialidadDTO);
        return ResponseEntity.ok(personaEspecialidadDTO);
    }
    @PostMapping("/nuevoEntrenador")
    public ResponseEntity<PersonaEspecialidadCrearDTO> nuevoEntrenador(@RequestBody PersonaEspecialidadCrearDTO personaEspecialidadDTO) {
        personaEspecialidadService.crearEntrenador(personaEspecialidadDTO);
        return ResponseEntity.ok(personaEspecialidadDTO);
    }
    @PostMapping("/nuevoJuez")
    public ResponseEntity<PersonaEspecialidadCrearDTO> nuevoJuez(@RequestBody PersonaEspecialidadCrearDTO personaEspecialidadDTO) {
        personaEspecialidadService.crearJuez(personaEspecialidadDTO);
        return ResponseEntity.ok(personaEspecialidadDTO);
    }

    @GetMapping("/personas")
    public ResponseEntity<List<PersonaEspecialidadFindAllDto>> obtenerPersonas() {
        return ResponseEntity.ok(personaEspecialidadService.findAll());
    }

    @PostMapping("/nuevaLicencia")
    public ResponseEntity<CrearLicenciaDTO> crearLicencia(@RequestBody CrearLicenciaDTO crearLicenciaDTO) {

        return ResponseEntity.ok(personaEspecialidadService.crearNuevaLicencia(crearLicenciaDTO));
    }
    @GetMapping("/licencias/{id}")
    public ResponseEntity<List<LicenciasActivasDTO>> obtenerLicencias(@PathVariable Long id) {
        return ResponseEntity.ok(personaEspecialidadService.licenciasActivas(id));
    }
}
