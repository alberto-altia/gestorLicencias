package proyectoFCT.gestorLicencias.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoFCT.gestorLicencias.service.impl.EspecialidadesServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EspecialidadesRestController {
    @Autowired
    EspecialidadesServiceImpl especialidadesService;

    @GetMapping("/especialidades")
    public ResponseEntity<?> allEspecialidades(){
        return ResponseEntity.ok(especialidadesService.findAll());
    }
}
