package proyectoFCT.gestorLicencias.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.service.ClubService;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class ClubRestController {
    @Autowired
    ClubService clubService;

    @PostMapping("/nuevoClub")
    public ResponseEntity<ClubDTO> nueva( @RequestBody ClubDTO clubDTO) throws IOException {
        clubService.crearClub(clubDTO);
        return ResponseEntity.ok(clubDTO);
    }
}
