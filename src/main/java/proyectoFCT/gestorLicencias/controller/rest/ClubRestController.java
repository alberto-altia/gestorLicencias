package proyectoFCT.gestorLicencias.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.service.ClubService;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClubRestController {
    @Autowired
    ClubService clubService;

    @PostMapping("/nuevoClub")
    public ResponseEntity<ClubDTO> nueva( @RequestBody ClubDTO clubDTO) throws IOException {
        return ResponseEntity.ok(clubService.crearOuModificarClub(clubDTO));
    }
}
