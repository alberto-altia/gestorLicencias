package proyectoFCT.gestorLicencias.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoFCT.gestorLicencias.controller.exceptions.BadRequestException;
import proyectoFCT.gestorLicencias.domain.dto.ClubDTO;
import proyectoFCT.gestorLicencias.service.ClubService;

import javax.persistence.EntityNotFoundException;
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

    @GetMapping("/clubs")
    public ResponseEntity<?> allClubs(){
        try{
            return ResponseEntity.ok(clubService.findAll());
        }catch(EntityNotFoundException e) {
            throw new BadRequestException("id incorrecto");
        }
    }
    @GetMapping("/clubs/{id}")
    public ResponseEntity<?> findClubByIdPersona(@PathVariable Long id){
        try{
            return ResponseEntity.ok(clubService.findClubByPersonaId(id));
        }catch(EntityNotFoundException e) {
            throw new BadRequestException("id incorrecto");
        }
    }
    @GetMapping("/deportistas/{id}")
    public ResponseEntity<?> findPersonasByIdClub(@PathVariable Long id){
        try{
            return ResponseEntity.ok(clubService.findPersonaByIdClub(id));
        }catch(EntityNotFoundException e) {
            throw new BadRequestException("id incorrecto");
        }
    }
}
